@echo off
echo ====================================
echo Modern Gallery - Android 构建脚本
echo ====================================
echo.

:: 检查 Java 环境
echo [1/5] 检查 Java 环境...
java -version >nul 2>&1
if errorlevel 1 (
    echo [错误] 未检测到 Java！
    echo 请安装 JDK 17 或更高版本
    echo 下载地址: https://adoptium.net/
    pause
    exit /b 1
)
java -version
echo.

:: 检查 Android SDK
echo [2/5] 检查 Android SDK...
if "%ANDROID_HOME%"=="" (
    if exist "%LOCALAPPDATA%\Android\Sdk" (
        set ANDROID_HOME=%LOCALAPPDATA%\Android\Sdk
        echo 找到 SDK: %ANDROID_HOME%
    ) else (
        echo [错误] 未检测到 Android SDK！
        echo 请安装 Android Studio 或手动配置 ANDROID_HOME 环境变量
        pause
        exit /b 1
    )
) else (
    echo SDK 路径: %ANDROID_HOME%
)
echo.

:: 清理项目
echo [3/5] 清理项目...
if exist "app\build" rmdir /s /q "app\build"
if exist "build" rmdir /s /q "build"
echo 清理完成
echo.

:: 构建 Debug APK
echo [4/5] 构建 Debug APK...
call gradlew.bat assembleDebug
if errorlevel 1 (
    echo [错误] 构建失败！
    pause
    exit /b 1
)
echo.

:: 显示结果
echo [5/5] 构建完成！
echo.
echo ====================================
echo APK 位置:
echo %cd%\app\build\outputs\apk\debug\app-debug.apk
echo ====================================
echo.
echo 安装命令: adb install app\build\outputs\apk\debug\app-debug.apk
echo.
pause

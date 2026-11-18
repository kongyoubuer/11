# Modern Gallery - 现代化 Android 图库应用

一款功能完整的 Android 图片/视频浏览应用，采用最新的 Android 开发技术栈。

## ✨ 功能特性

### 核心功能
- **首页** - 网格布局展示媒体内容，支持分类筛选
- **分类页面** - 按类别组织内容（自然、城市、美食、科技等）
- **详情页面** - 查看大图、播放视频、查看详细信息
- **设置页面** - 主题切换、通知管理、存储管理

### 技术亮点
- ✅ Material Design 3 现代化 UI
- ✅ 支持深色模式（系统跟随 + 动态颜色）
- ✅ 响应式布局，适配不同屏幕尺寸
- ✅ 图片加载优化（Coil）
- ✅ 视频播放支持（ExoPlayer）
- ✅ 本地数据库存储（Room）
- ✅ 收藏功能
- ✅ 浏览记录统计

## 🏗️ 技术架构

### 开发语言与框架
- **语言**: Kotlin
- **最低 SDK**: API 24 (Android 7.0)
- **目标 SDK**: API 34 (Android 14)
- **UI 框架**: Jetpack Compose

### 架构模式
- **MVVM** (Model-View-ViewModel)
- **单向数据流** (UDF)
- **Repository 模式**

### 核心依赖库
| 功能 | 库 | 版本 |
|------|-----|------|
| UI 框架 | Jetpack Compose | BOM 2023.10.01 |
| 导航 | Navigation Compose | 2.7.5 |
| 依赖注入 | Hilt | 2.48 |
| 数据库 | Room | 2.6.0 |
| 网络请求 | Retrofit + OkHttp | 2.9.0 / 4.12.0 |
| 图片加载 | Coil | 2.5.0 |
| 视频播放 | ExoPlayer (Media3) | 1.2.0 |
| 异步处理 | Kotlin Coroutines | 1.7.3 |

## 📁 项目结构

```
MyModernApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/modernapp/gallery/
│   │   │   ├── data/                    # 数据层
│   │   │   │   ├── local/              # 本地数据库
│   │   │   │   │   ├── AppDatabase.kt
│   │   │   │   │   └── MediaDao.kt
│   │   │   │   ├── model/              # 数据模型
│   │   │   │   │   └── MediaItem.kt
│   │   │   │   └── repository/         # 仓储层
│   │   │   │       └── MediaRepository.kt
│   │   │   ├── di/                     # 依赖注入
│   │   │   │   └── DatabaseModule.kt
│   │   │   ├── ui/                     # UI 层
│   │   │   │   ├── navigation/         # 导航
│   │   │   │   │   └── Screen.kt
│   │   │   │   ├── screens/            # 页面
│   │   │   │   │   ├── HomeScreen.kt
│   │   │   │   │   ├── CategoryScreen.kt
│   │   │   │   │   ├── DetailScreen.kt
│   │   │   │   │   └── SettingsScreen.kt
│   │   │   │   ├── theme/              # 主题
│   │   │   │   │   ├── Color.kt
│   │   │   │   │   ├── Theme.kt
│   │   │   │   │   └── Type.kt
│   │   │   │   └── viewmodel/          # ViewModel
│   │   │   │       ├── HomeViewModel.kt
│   │   │   │       ├── CategoryViewModel.kt
│   │   │   │       └── DetailViewModel.kt
│   │   │   ├── MainActivity.kt         # 主 Activity
│   │   │   └── MyModernApplication.kt  # Application 类
│   │   ├── res/                        # 资源文件
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## 🚀 构建与安装

### 环境要求
- **JDK**: 17 或更高版本
- **Android Studio**: Hedgehog (2023.1.1) 或更新版本
- **Gradle**: 8.2+（通过 wrapper 自动下载）
- **Android SDK**: API 34

### 构建步骤

#### 1. 安装 Android Studio
从 [官网](https://developer.android.com/studio) 下载并安装 Android Studio。

#### 2. 配置 Android SDK
打开 Android Studio，进入 `Tools > SDK Manager`，安装：
- Android SDK Platform 34
- Android SDK Build-Tools 34.0.0
- Android SDK Command-line Tools

#### 3. 克隆/打开项目
将项目文件夹 `E:\ai\MyModernApp` 用 Android Studio 打开。

#### 4. 同步 Gradle
Android Studio 会自动提示同步 Gradle，点击 "Sync Now"。

#### 5. 构建 APK

**方式 1：Android Studio**
- 菜单栏: `Build > Build Bundle(s) / APK(s) > Build APK(s)`
- 等待构建完成，APK 位于: `app/build/outputs/apk/debug/app-debug.apk`

**方式 2：命令行**
```bash
cd E:\ai\MyModernApp
.\gradlew assembleDebug
```

#### 6. 生成签名的 Release APK

**创建签名密钥:**
```bash
keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias
```

**配置签名（在 app/build.gradle.kts）:**
```kotlin
android {
    signingConfigs {
        create("release") {
            storeFile = file("../my-release-key.jks")
            storePassword = "your_password"
            keyAlias = "my-key-alias"
            keyPassword = "your_password"
        }
    }
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            // ... 其他配置
        }
    }
}
```

**构建 Release APK:**
```bash
.\gradlew assembleRelease
```

输出位置: `app/build/outputs/apk/release/app-release.apk`

### 安装 APK

**方式 1：USB 连接设备**
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

**方式 2：直接拷贝**
将 APK 文件复制到 Android 设备，直接点击安装。

## 🎨 UI 设计特点

### Material Design 3
- 采用最新的 Material You 设计语言
- 支持动态颜色（Android 12+）
- 流畅的动画过渡
- 现代化的圆角卡片设计

### 适配特性
- 响应式网格布局（2 列）
- 自适应不同屏幕尺寸（手机/平板）
- 状态栏和导航栏透明化
- 深色模式完整支持

## 📊 性能优化

### 启动优化
- 使用 Splash Screen API
- 延迟初始化
- ProGuard 代码混淆和压缩

### 运行优化
- Coil 图片缓存和内存管理
- Room 数据库索引优化
- Kotlin Coroutines 异步处理
- Flow 响应式数据流

### 内存优化
- 图片按需加载
- 视频播放器生命周期管理
- 避免内存泄漏（ViewModel + Lifecycle）

## 🧪 测试

### 运行单元测试
```bash
.\gradlew test
```

### 运行 UI 测试
```bash
.\gradlew connectedAndroidTest
```

## 📝 开发说明

### 添加新的媒体分类
在 `MediaItem.kt` 中添加新的枚举值：
```kotlin
enum class MediaCategory(val displayName: String) {
    // ... 现有分类
    NEW_CATEGORY("新分类")
}
```

### 修改主题颜色
编辑 `ui/theme/Color.kt` 文件，修改主题色值。

### 自定义示例数据
在 `MediaRepository.kt` 的 `initializeSampleData()` 方法中修改示例数据。

## 🐛 故障排除

### 构建失败
1. 确认 JDK 版本为 17+
2. 清理项目: `.\gradlew clean`
3. 删除 `.gradle` 文件夹并重新同步

### 运行时崩溃
1. 检查 Logcat 错误信息
2. 确认设备 API 级别 >= 24
3. 清除应用数据后重试

### 图片加载失败
1. 检查网络权限
2. 确认网络连接
3. 查看 Logcat 中的 Coil 日志

## 📄 许可证

本项目仅用于学习和演示目的。

## 🙏 致谢

项目使用了以下优秀的开源库：
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt](https://dagger.dev/hilt/)
- [Room](https://developer.android.com/training/data-storage/room)
- [Retrofit](https://square.github.io/retrofit/)
- [Coil](https://coil-kt.github.io/coil/)
- [ExoPlayer](https://exoplayer.dev/)

---

**开发工具**: Android Studio Hedgehog | 2023.1.1  
**构建系统**: Gradle 8.2  
**开发语言**: Kotlin 1.9.20  
**最后更新**: 2026-11-18

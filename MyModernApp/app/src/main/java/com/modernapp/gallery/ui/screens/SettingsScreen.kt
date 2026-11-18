package com.modernapp.gallery.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    var darkThemeEnabled by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var autoPlayVideos by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogType by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("设置") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            // 外观设置
            item {
                SettingsSection(title = "外观")
            }

            item {
                SettingItem(
                    icon = Icons.Default.DarkMode,
                    title = "深色模式",
                    subtitle = "切换应用主题",
                    trailing = {
                        Switch(
                            checked = darkThemeEnabled,
                            onCheckedChange = { darkThemeEnabled = it }
                        )
                    }
                )
            }

            item { Divider(modifier = Modifier.padding(horizontal = 16.dp)) }

            // 播放设置
            item {
                SettingsSection(title = "播放")
            }

            item {
                SettingItem(
                    icon = Icons.Default.VideoSettings,
                    title = "自动播放视频",
                    subtitle = "在详情页自动播放视频",
                    trailing = {
                        Switch(
                            checked = autoPlayVideos,
                            onCheckedChange = { autoPlayVideos = it }
                        )
                    }
                )
            }

            item { Divider(modifier = Modifier.padding(horizontal = 16.dp)) }

            // 通知设置
            item {
                SettingsSection(title = "通知")
            }

            item {
                SettingItem(
                    icon = Icons.Default.Notifications,
                    title = "推送通知",
                    subtitle = "接收新内容通知",
                    trailing = {
                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it }
                        )
                    }
                )
            }

            item { Divider(modifier = Modifier.padding(horizontal = 16.dp)) }

            // 存储设置
            item {
                SettingsSection(title = "存储")
            }

            item {
                SettingItem(
                    icon = Icons.Default.Storage,
                    title = "清除缓存",
                    subtitle = "释放存储空间",
                    onClick = {
                        dialogType = "cache"
                        showDialog = true
                    }
                )
            }

            item {
                SettingItem(
                    icon = Icons.Default.DeleteForever,
                    title = "清除所有数据",
                    subtitle = "删除所有本地数据",
                    onClick = {
                        dialogType = "data"
                        showDialog = true
                    }
                )
            }

            item { Divider(modifier = Modifier.padding(horizontal = 16.dp)) }

            // 关于
            item {
                SettingsSection(title = "关于")
            }

            item {
                SettingItem(
                    icon = Icons.Default.Info,
                    title = "应用版本",
                    subtitle = "1.0.0",
                    onClick = {}
                )
            }

            item {
                SettingItem(
                    icon = Icons.Default.Description,
                    title = "隐私政策",
                    subtitle = "查看隐私政策",
                    onClick = {}
                )
            }

            item {
                SettingItem(
                    icon = Icons.Default.Gavel,
                    title = "用户协议",
                    subtitle = "查看使用条款",
                    onClick = {}
                )
            }

            item {
                SettingItem(
                    icon = Icons.Default.Code,
                    title = "开源许可",
                    subtitle = "查看开源组件",
                    onClick = {}
                )
            }
        }
    }

    // 确认对话框
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
            },
            title = {
                Text(
                    text = when (dialogType) {
                        "cache" -> "清除缓存"
                        "data" -> "清除所有数据"
                        else -> ""
                    }
                )
            },
            text = {
                Text(
                    text = when (dialogType) {
                        "cache" -> "这将清除所有缓存数据，确定继续吗？"
                        "data" -> "这将删除所有本地数据，包括收藏和浏览记录，此操作不可恢复。确定继续吗？"
                        else -> ""
                    }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // 执行清除操作
                        showDialog = false
                    }
                ) {
                    Text("确定")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("取消")
                }
            }
        )
    }
}

@Composable
fun SettingsSection(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun SettingItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    trailing: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (trailing != null) {
                trailing()
            } else {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

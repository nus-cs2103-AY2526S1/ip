# Windows测试指南

## 方法1: 一行命令（最简单）

打开CMD或PowerShell，在buddy.jar所在目录运行：

```cmd
echo list & echo bye | java -Djava.awt.headless=true -jar buddy.jar
```

如果看到以下输出，说明JAR正常工作：
```
Buddy is running in CLI mode...
Hello! I'm Buddy
Bye. Hope to see you again soon!
```

## 方法2: 批处理文件（更详细测试）

1. 下载 `test-windows.bat` 文件
2. 把 `buddy.jar` 和 `test-windows.bat` 放在同一文件夹
3. 双击运行 `test-windows.bat`

## 方法3: PowerShell命令（更多测试）

```powershell
"list","todo Test","list","bye" | java -Djava.awt.headless=true -jar buddy.jar
```

## 注意事项

- 需要安装Java 17或更高版本
- 如果提示"java不是内部或外部命令"，需要先安装Java
- 下载Java: https://adoptium.net/

## 快速检查Java是否安装

```cmd
java -version
```

## 分享给朋友的消息模板

```
Hi! 能帮我测试一下这个JAR文件吗？

1. 下载buddy.jar
2. 打开CMD，进入文件所在目录
3. 运行: echo list & echo bye | java -Djava.awt.headless=true -jar buddy.jar
4. 告诉我是否看到"Buddy is running in CLI mode"和"Bye. Hope to see you again soon!"

谢谢！
```
# Personal Business Card Generator

一个功能完整的Android数字名片生成器应用，支持多模板选择、二维码生成、信息编辑和分享。

## 功能特性

### 1. 名片信息编辑
- ✅ 编辑姓名、职位、电话、邮箱、公司/学校、个人简介
- ✅ 支持头像选择（预留接口）
- ✅ 实时预览编辑效果

### 2. 多模板选择
- ✅ **简约白模板**（Minimal White）- 经典简洁风格
- ✅ **科技蓝模板**（Tech Blue）- 现代科技感
- ✅ **学术灰模板**（Academic Gray）- 学术专业风格
- ✅ 支持实时切换预览

### 3. 二维码生成
- ✅ 自动生成vCard格式的二维码
- ✅ 支持扫码自动保存联系人
- ✅ 使用ZXing库生成高质量二维码

### 4. 分享功能
- ✅ 名片截图保存到相册（Android 10+ 适配）
- ✅ 通过系统分享面板分享联系信息
- ✅ 支持分享给微信、QQ等应用

### 5. 数据持久化
- ✅ 使用DataStore保存用户编辑的名片信息
- ✅ 应用重新打开时自动恢复上次的编辑数据
- ✅ 支持多套名片数据的扩展

## 技术栈

### 核心技术
- **语言**: Kotlin + Java
- **UI框架**: AndroidX, ConstraintLayout, RecyclerView
- **数据存储**: DataStore Preferences
- **二维码生成**: ZXing Library (3.5.1)
- **异步处理**: Coroutines
- **权限管理**: 运行时权限请求

### 技术亮点
1. **DataStore数据持久化** - 现代Android数据存储方案
2. **RecyclerView动态模板渲染** - 灵活的模板系统
3. **权限管理** - 适配Android 13+ 新权限模型
4. **Canvas截图** - 动态生成名片图片
5. **vCard格式** - 标准联系人格式支持

## 项目结构

```
Personal-Business-Card-Generator/
├── src/main/
│   ├── java/com/example/businesscardgenerator/
│   │   ├── data/
│   │   │   └── CardDataStore.kt          # 数据存储层
│   │   ├── ui/
│   │   │   ├── MainActivity.kt           # 主编辑界面
│   │   │   ├── CardDetailActivity.kt     # 名片预览界面
│   │   │   ├── TemplateSelectActivity.kt # 模板选择界面
│   │   │   ├── adapter/
│   │   │   │   └── TemplateAdapter.kt    # RecyclerView适配器
│   │   │   └── template/
│   │   │       └── CardTemplate.kt       # 模板定义和工厂
│   │   └── utils/
│   │       ├── QRCodeGenerator.kt        # 二维码生成
│   │       ├── ScreenshotHelper.kt       # 截图和保存
│   │       └── PermissionHelper.kt       # 权限管理
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml
│   │   │   ├── activity_card_detail.xml
│   │   │   └── activity_template_select.xml
│   │   ├── values/
│   │   │   ├── strings.xml
│   │   │   └── colors.xml
│   │   └── xml/
│   │       └── file_paths.xml
│   └── AndroidManifest.xml
├── build.gradle.kts
└── README.md
```

## 快速开始

### 环境要求
- Android Studio 最新版本
- Android SDK 26+ (Oreo)
- Gradle 8.0+
- Kotlin 1.9+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/Christy-Xiao/Personal-Business-Card-Generator.git
   cd Personal-Business-Card-Generator
   ```

2. **在Android Studio中打开**
   - File → Open → 选择项目目录
   - 等待Gradle同步完成

3. **配置权限**
   - AndroidManifest.xml已配置所需权限
   - 运行时会请求相机和存储权限

4. **编译运行**
   ```bash
   ./gradlew installDebug
   ```
   或在Android Studio中点击Run

## 使用指南

### 基本流程

1. **编辑名片信息**
   - 在主界面输入个人信息
   - 点击"预览"查看效果

2. **选择模板**
   - 点击"选择模板"按钮
   - 从3种模板中选择喜欢的风格
   - 系统自动保存选择

3. **预览和分享**
   - 点击"预览"查看完整的名片和二维码
   - 点击"保存截图"将名片保存到相册
   - 点击"分享"通过其他应用分享联系信息

4. **数据持久化**
   - 所有编辑自动保存到本地
   - 下次打开应用时自动恢复数据

## 权限说明

| 权限 | 用途 | Android版本 |
|------|------|----------|
| CAMERA | 拍照作为头像 | 所有版本 |
| READ_EXTERNAL_STORAGE | 从相册选择头像 | Android 6-12 |
| READ_MEDIA_IMAGES | 从相册选择头像 | Android 13+ |
| WRITE_EXTERNAL_STORAGE | 保存截图到相册 | Android 6-9 |

## API说明

### CardDataStore
```kotlin
// 获取完整的名片数据
dataStore.getCardData(): Flow<BusinessCardData>

// 保存名片数据
suspend fun saveCardData(cardData: BusinessCardData)

// 清空所有数据
suspend fun clearAll()
```

### QRCodeGenerator
```kotlin
// 生成vCard格式
fun generateVCard(cardData: BusinessCardData): String

// 生成QR码图片
fun generateQRCode(vCardData: String, size: Int = 512): Bitmap?

// 生成分享文本
fun generateShareText(cardData: BusinessCardData): String
```

### ScreenshotHelper
```kotlin
// 截图
fun captureViewAsBitmap(view: View): Bitmap

// 保存到相册
fun saveBitmapToGallery(context: Context, bitmap: Bitmap, fileName: String): Boolean
```

### TemplateFactory
```kotlin
// 获取指定模板
fun getTemplate(templateId: String): CardTemplate

// 获取所有模板
fun getAllTemplates(): List<CardTemplate>
```

## 扩展功能建议

### 短期计划
- [ ] 集成图片裁剪库实现头像编辑
- [ ] 支持多套名片数据管理
- [ ] 添加更多模板风格
- [ ] 实现名片导入导出功能

### 中期计划
- [ ] 云端同步功能
- [ ] 名片分享二维码链接
- [ ] 支持自定义颜色方案
- [ ] 动画效果优化

### 长期计划
- [ ] Web版本支持
- [ ] 企业名片批量生成
- [ ] AI头像生成
- [ ] 社交分享集成

## 常见问题

### Q: 如何修改模板样式？
A: 编辑 `CardTemplate.kt` 中对应模板类的 `createView` 方法，修改颜色、大小等属性。

### Q: 二维码包含的信息有哪些？
A: 二维码包含vCard格式的联系信息，支持所有主流手机的扫码保存。

### Q: 数据存储在哪里？
A: 使用DataStore保存在应用的私有目录（`/data/data/com.example.businesscardgenerator/`）。

### Q: 如何备份名片数据？
A: 可以通过分享功能导出文本，或实现备份导出功能。

## 故障排除

### 编译错误
- 确保Gradle版本 ≥ 8.0
- 更新Android Gradle Plugin到最新版本
- 清除缓存：`./gradlew clean`

### 运行时权限
- Android 6+设备需要手动授予权限
- 在应用设置中检查权限状态

### 二维码生成失败
- 检查ZXing库是否正确导入
- 验证vCard数据格式

## 许可证

MIT License - 详见LICENSE文件

## 贡献指南

欢迎提交Issue和Pull Request！

## 联系方式

- GitHub: [@Christy-Xiao](https://github.com/Christy-Xiao)
- Email: 3230573842@qq.com

---

**最后更新**: 2026年6月16日

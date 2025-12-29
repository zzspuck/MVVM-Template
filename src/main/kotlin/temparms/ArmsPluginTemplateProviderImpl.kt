package temparms

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import other.mvvm.temparms.armsRecipe
import java.io.File

enum class PageType {
    Activity,
    Fragment
}

class ArmsPluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
            armsTemplate
    )

    val armsTemplate: Template
        get() = template {
            name = "Mvvm 全家桶 插件版"
            description = "一键创建 JetpackMvvm单个页面所需要的全部组件"
            minApi = MIN_API
            category = Category.Activity
            formFactor = FormFactor.Mobile
            screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)
            thumb { File("template_blank_activity.png") }

            widgets(
                    TextFieldWidget(pageName),
                    PackageNameWidget(appPackageName),
                    TextFieldWidget(appPackageName),
                    EnumWidget(pageType),
                    CheckBoxWidget(useCompose),
                    CheckBoxWidget(isModule),
                    TextFieldWidget(layoutName),
                    CheckBoxWidget(generateLayout),
                    TextFieldWidget(pagePackageName),
                    CheckBoxWidget(needViewModel),
                    TextFieldWidget(viewModelPackageName),
                    CheckBoxWidget(needRepository),
                    TextFieldWidget(repositoryPackageName),
                    CheckBoxWidget(needModel),
                    TextFieldWidget(modelPackageName)
//                    CheckBoxWidget(needDagger),
//                    TextFieldWidget(componentPackageName),
//                    TextFieldWidget(moudlePackageName)
            )

            //创建所需文件
            recipe = { te ->
                armsRecipe(this@ArmsPluginTemplateProviderImpl, (te as ModuleTemplateData))
            }
        }


    //新建页面名称
    val pageName = stringParameter {
        name = "Page Name"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY,Constraint.STRING)
        default = "Main"
        help = "请填写页面名,如填写 Main,会自动生成 MainActivity, MainViewModel 等文件"
    }

    //包名
    val appPackageName = stringParameter {
        name = "Root Package Name"
        constraints = listOf(Constraint.PACKAGE)
        default = "com.lovemaps.app"
        help = "请填写你的项目包名,请认真核实此包名是否是正确的项目包名,不能包含子包,正确的格式如:com.lovemaps.app"
    }

    //页面类型 (Activity 或 Fragment)
    val pageType = enumParameter<PageType> {
        name = "Page Type"
        default = PageType.Activity
        help = "选择要生成的页面类型: Activity 或 Fragment (二选一)"
    }

    //是否使用 Compose 版本
    val useCompose = booleanParameter {
        name = "Use Compose Version"
        default = false
        help = "是否使用 Compose 版本 (BaseComposeActivity/BaseComposeFragment)? 勾选则生成 Compose 版本,否则生成普通的 BaseMvvm 版本"
    }

    //组件化相关
    val isModule = booleanParameter {
        name = "Is Module"
        default = true
        help = "是否是组件化模块，如果是就会在两个AndroidManifest.xml都加上activity标签"
    }

    //layout xml 文件 (根据页面类型动态显示)
    val layoutName = stringParameter {
        name = "Layout Name"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
        suggest = {
            when (pageType.value) {
                PageType.Activity -> activityToLayout(pageName.value)
                PageType.Fragment -> "fragment_${classToResource(pageName.value)}"
            }
        }
        default = "activity_main"
        visible = { !useCompose.value }
        help = "页面创建之前需要填写布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框 (Compose 版本不需要布局文件)"
    }

    //是否需要 layout xml 文件
    val generateLayout = booleanParameter {
        name = "Generate Layout"
        default = true
        visible = { !useCompose.value }
        help = "是否需要生成布局? 若勾选,则使用上面的布局名创建默认的布局 (Compose 版本不需要布局文件)"
    }

    //页面路径 (根据页面类型动态显示)
    val pagePackageName = stringParameter {
        name = "Page Package Name"
        constraints = listOf(Constraint.STRING)
        suggest = {
            when (pageType.value) {
                PageType.Activity -> "${appPackageName.value}.mvvm.ui.activity"
                PageType.Fragment -> "${appPackageName.value}.mvvm.ui.fragment"
            }
        }
        default = "${appPackageName.value}.mvvm.ui.activity"
        help = "页面将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    // mvvm 相关
    val needViewModel = booleanParameter {
        name = "Generate ViewModel"
        default = true
        help = "是否需要生成 ViewModel ? 不勾选则不生成"
    }

    val viewModelPackageName = stringParameter {
        name = "ViewModel Package Name"
        constraints = listOf(Constraint.STRING)
        suggest = { "${appPackageName.value}.mvvm.viewmodel" }
        visible = { needViewModel.value }
        default = "${appPackageName.value}.mvvm.viewmodel"
        help = "Contract 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    val needRepository = booleanParameter {
        name = "Generate Repository"
        default = true
        help = "是否需要生成 repository ? 不勾选则不生成"
    }

    val repositoryPackageName = stringParameter {
        name = "Repository Package Name"
        constraints = listOf(Constraint.STRING)
        suggest = { "${appPackageName.value}.mvvm.repository" }
        visible = { needRepository.value }
        default ="${appPackageName.value}.mvvm.repository"
        help = "Repository 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    val needModel = booleanParameter {
        name = "Generate Model"
        default = true
        help = "是否需要生成 Model ? 不勾选则不生成"
    }

    val modelPackageName = stringParameter {
        name = "Model Package Name"
        constraints = listOf(Constraint.STRING)
        suggest = { "${appPackageName.value}.mvvm.model" }
        visible = { needModel.value }
        default ="${appPackageName.value}.mvvm.model"
        help = "Model 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"

    }

//    //dagger 相关
//    val needDagger = booleanParameter {
//        name = "Generate Dagger (Moudle And Component)"
//        default = true
//        help = "是否需要生成 Dagger 组件? 不勾选则不生成"
//    }
//    val componentPackageName = stringParameter {
//        name = "Component Package Name"
//        constraints = listOf(Constraint.STRING)
//        suggest = { "${appPackageName.value}.di.component" }
//        visible = { needDagger.value }
//        default = "${appPackageName.value}.di.component"
//        help = "Component 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }
//    val moudlePackageName = stringParameter {
//        name = "Moudle Package Name"
//        constraints = listOf(Constraint.STRING)
//        suggest = { "${appPackageName.value}.di.module" }
//        visible = { needDagger.value }
//        default = "${appPackageName.value}.di.module"
//        help = "Moudle 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }

}
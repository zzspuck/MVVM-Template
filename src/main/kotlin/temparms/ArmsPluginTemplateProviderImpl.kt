package temparms

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import other.mvvm.temparms.armsRecipe
import java.io.File

class ArmsPluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
            armsTemplate
    )

    val armsTemplate: Template
        get() = template {
            name = "Jetpack mvvm 全家桶 插件版"
            description = "一键创建 JetpackMvvm(被telyo扩展后)单个页面所需要的全部组件"
            minApi = MIN_API
            category = Category.Activity
            formFactor = FormFactor.Mobile
            screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)
            thumb { File("template_blank_activity.png") }

            widgets(
                    TextFieldWidget(pageName),
                    PackageNameWidget(appPackageName),
                    TextFieldWidget(appPackageName),
                    CheckBoxWidget(needActivity),
                    CheckBoxWidget(isModule),
                    TextFieldWidget(activityLayoutName),
                    CheckBoxWidget(generateActivityLayout),
                    TextFieldWidget(activityPackageName),
                    CheckBoxWidget(needFragment),
                    TextFieldWidget(fragmentLayoutName),
                    CheckBoxWidget(generateFragmentLayout),
                    TextFieldWidget(fragmentPackageName),
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
        default = "com.mycompany.myapp"
        help = "请填写你的项目包名,请认真核实此包名是否是正确的项目包名,不能包含子包,正确的格式如:me.jessyan.arms"
    }

    //是否需要
    val needActivity = booleanParameter {
        name = "Generate Activity"
        default = true
        help = "是否需要生成 Activity ? 不勾选则不生成"
    }

    //组件化相关
    val isModule = booleanParameter {
        name = "Is Module"
        default = true
        help = "是否是组件化模块，如果是就会在两个AndroidManifest.xml都加上activity标签"
    }

    //layout xml 文件
    val activityLayoutName = stringParameter {
        name = "Activity Layout Name"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
        suggest = { activityToLayout(pageName.value) }
        default = "activity_main"
        visible = { needActivity.value }
        help = "Activity 创建之前需要填写 Activity 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
    }

    //是否需要 layout xml 文件
    val generateActivityLayout = booleanParameter {
        name = "Generate Activity Layout"
        default = true
        visible = { needActivity.value }
        help = "是否需要给 Activity 生成布局? 若勾选,则使用上面的布局名给此 Activity 创建默认的布局"
    }

    //Activity 路径
    val activityPackageName = stringParameter {
        name = "Ativity Package Name"
        constraints = listOf(Constraint.STRING)
        suggest = { "${appPackageName.value}.mvvm.ui.activity" }
        visible = { needActivity.value }
        default ="${appPackageName.value}.mvvm.ui.activity"
        help = "Activity 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    //是否需要生成 Fragment
    val needFragment = booleanParameter {
        name = "Generate Fragment"
        default = false
        help = "是否需要生成 Fragment ? 不勾选则不生成"
    }

    //Fragment xml 文件
    val fragmentLayoutName = stringParameter {
        name = "Fragment Layout Name"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
        suggest = { "fragment_${classToResource(pageName.value)}" }
        default = "fragment_main"
        visible = { needFragment.value }
        help = "Fragment 创建之前需要填写 Fragment 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
    }

    //是否需要生成 Fragment layout 文件
    val generateFragmentLayout = booleanParameter {
        name = "Generate Fragment Layout"
        default = true
        visible = { needFragment.value }
        help = "是否需要给 Fragment 生成布局? 若勾选,则使用上面的布局名给此 Fragment 创建默认的布局"
    }

    //fragment 路径
    val fragmentPackageName = stringParameter {
        name = "Fragment Package Name"
        constraints = listOf(Constraint.STRING)
        suggest = { "${appPackageName.value}.mvvm.ui.fragment" }
        visible = { needFragment.value }
        default = "${appPackageName.value}.mvvm.ui.fragment"
        help = "Fragment 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
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
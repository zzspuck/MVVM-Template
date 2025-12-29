package other.mvvm.temparms

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvvm.temparms.res.layout.simpleLayout
import other.mvvm.temparms.src.app_package.armsActivity
import other.mvvm.temparms.src.app_package.armsComposeActivity
import other.mvvm.temparms.src.app_package.armsComposeFragment
import other.mvvm.temparms.src.app_package.armsContract
import other.mvvm.temparms.src.app_package.armsFragment
import other.mvvm.temparms.src.app_package.armsRepository
import other.mvvm.temparms.src.armsManifest
import temparms.ArmsPluginTemplateProviderImpl
import java.io.File

fun RecipeExecutor.armsRecipe(provider: ArmsPluginTemplateProviderImpl, data: ModuleTemplateData){

    val isActivity = provider.pageType.value == temparms.PageType.Activity
    val isCompose = provider.useCompose.value

    // 调试输出
    println("=== MVVM Template Debug ===")
    println("Page Name: ${provider.pageName.value}")
    println("Page Type: ${provider.pageType.value}")
    println("Is Activity: $isActivity")
    println("Use Compose: $isCompose")
    println("Package Name: ${provider.pagePackageName.value}")
    println("=========================")

    // 生成 AndroidManifest.xml (仅 Activity 需要)
    if (isActivity) {
        if (!provider.isModule.value){
            mergeXml(armsManifest(provider), File(data.manifestDir,"AndroidManifest.xml" ))
        } else {
            mergeXml(armsManifest(provider), File(data.manifestDir,"../debug/AndroidManifest.xml" ))
            mergeXml(armsManifest(provider), File(data.manifestDir,"../release/AndroidManifest.xml" ))
        }
    }

    // 生成布局文件 (仅非 Compose 版本需要)
    if (!isCompose && provider.generateLayout.value){
        save(simpleLayout(provider), File(data.resDir,"layout/${provider.layoutName.value}.xml" ))
    }

    // 根据页面类型和 Compose 选项生成相应文件
    if (isActivity) {
        // 生成 Activity
        val activityFile = File(data.rootDir,"${fFmSlashedPackageName(provider.pagePackageName.value)}/${provider.pageName.value}Activity.kt")
        println("Activity file path: ${activityFile.absolutePath}")
        if (isCompose) {
            println("Generating Compose Activity...")
            save(armsComposeActivity(provider), activityFile)
        } else {
            println("Generating Normal Activity...")
            save(armsActivity(provider), activityFile)
        }
        open(activityFile)
    } else {
        // 生成 Fragment
        val fragmentFile = File(data.rootDir,"${fFmSlashedPackageName(provider.pagePackageName.value)}/${provider.pageName.value}Fragment.kt")
        println("Fragment file path: ${fragmentFile.absolutePath}")
        if (isCompose) {
            println("Generating Compose Fragment...")
            save(armsComposeFragment(provider), fragmentFile)
        } else {
            println("Generating Normal Fragment...")
            save(armsFragment(provider), fragmentFile)
        }
        open(fragmentFile)
    }

    // 生成 ViewModel
    if (provider.needViewModel.value){
        val contractFile = File(data.rootDir,"${fFmSlashedPackageName(provider.viewModelPackageName.value)}/${provider.pageName.value}ViewModel.kt")
        save(armsContract(provider),contractFile)
    }

    // 生成 Repository
    if (provider.needRepository.value){
        val presenterFile = File(data.rootDir,"${fFmSlashedPackageName(provider.repositoryPackageName.value)}/${provider.pageName.value}Repository.kt")
        save(armsRepository(provider),presenterFile)
    }

    // 生成 Model (当前已注释)
    if (provider.needModel.value){
//        val modelFile = File(data.rootDir,"${fFmSlashedPackageName(provider.modelPackageName.value)}/${provider.pageName.value}Model.kt")
//        save(armsModel(provider),modelFile)
    }

//    if (provider.needDagger.value){
//        val componentFile = File(data.rootDir,"${fFmSlashedPackageName(provider.componentPackageName.value)}/${provider.pageName.value}Component.kt")
//        val moduleFile = File(data.rootDir,"${fFmSlashedPackageName(provider.moudlePackageName.value)}/${provider.pageName.value}Module.kt")
//        save(armsComponent(provider),componentFile)
//        save(armsModule(provider),moduleFile)
//    }
}
fun fFmSlashedPackageName(oVar:String): String {

    return "src/main/java/${oVar.replace('.', '/')}"
}
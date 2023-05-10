package other.mvvm.temparms

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvvm.temparms.res.layout.simpleLayout
import other.mvvm.temparms.src.app_package.armsActivity
import other.mvvm.temparms.src.app_package.armsContract
import other.mvvm.temparms.src.app_package.armsFragment
import other.mvvm.temparms.src.app_package.armsRepository
import other.mvvm.temparms.src.armsManifest
import temparms.ArmsPluginTemplateProviderImpl
import java.io.File

fun RecipeExecutor.armsRecipe(provider: ArmsPluginTemplateProviderImpl, data: ModuleTemplateData){

    if (provider.needActivity.value && !provider.isModule.value){
        mergeXml(armsManifest(provider), File(data.manifestDir,"AndroidManifest.xml" ))
    }
    if (provider.needActivity.value && provider.isModule.value){
        mergeXml(armsManifest(provider), File(data.manifestDir,"../debug/AndroidManifest.xml" ))
        mergeXml(armsManifest(provider), File(data.manifestDir,"../release/AndroidManifest.xml" ))
    }
    if (provider.needActivity.value && provider.generateActivityLayout.value){
        save(simpleLayout(provider), File(data.resDir,"layout/${provider.activityLayoutName.value}.xml" ))
    }
    if (provider.needFragment.value && provider.generateFragmentLayout.value){
        save(simpleLayout(provider), File(data.resDir,"layout/${provider.fragmentLayoutName.value}.xml" ))
    }
    if (provider.needActivity.value){
        val activityFile = File(data.rootDir,"${fFmSlashedPackageName(provider.activityPackageName.value)}/${provider.pageName.value}Activity.kt")
        save(armsActivity(provider),activityFile)
        open(activityFile)
    }
    if (provider.needFragment.value){
        val fragmentFile = File(data.rootDir,"${fFmSlashedPackageName(provider.fragmentPackageName.value)}/${provider.pageName.value}Fragment.kt")
        save(armsFragment(provider),fragmentFile)
        open(fragmentFile)
    }

    if (provider.needViewModel.value){
        val contractFile = File(data.rootDir,"${fFmSlashedPackageName(provider.viewModelPackageName.value)}/${provider.pageName.value}ViewModel.kt")
        save(armsContract(provider),contractFile)
    }
    if (provider.needRepository.value){
        val presenterFile = File(data.rootDir,"${fFmSlashedPackageName(provider.repositoryPackageName.value)}/${provider.pageName.value}Repository.kt")
        save(armsRepository(provider),presenterFile)
    }
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
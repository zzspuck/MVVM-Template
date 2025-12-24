package other.mvvm.temparms.src.app_package

import temparms.ArmsPluginTemplateProviderImpl

fun armsContract(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.viewModelPackageName.value}

import com.lovemaps.jetpackmvvm.base.viewmodel.BaseViewModel
import ${provider.repositoryPackageName.value}.${provider.pageName.value}Repository

class ${provider.pageName.value}ViewModel: BaseViewModel() {

    private val mRepository by lazy { ${provider.pageName.value}Repository() }

}
"""
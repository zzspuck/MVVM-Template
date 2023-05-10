package other.mvvm.temparms.src.app_package

import temparms.ArmsPluginTemplateProviderImpl

fun armsRepository(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.repositoryPackageName.value}

import com.love.jetpackmvvm.base.repository.BaseRepository

class ${provider.pageName.value}Repository: BaseRepository(){
    

}   
"""
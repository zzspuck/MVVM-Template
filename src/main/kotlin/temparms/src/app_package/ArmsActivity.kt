package other.mvvm.temparms.src.app_package

import temparms.ArmsPluginTemplateProviderImpl

fun armsActivity(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}

import android.os.Bundle
import com.lovemaps.core.base.BaseMvvmActivity
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ViewModel
import ${provider.appPackageName.value}.R

class ${provider.pageName.value}Activity : BaseMvvmActivity<${provider.pageName.value}ViewModel,Activity${provider.pageName.value}Binding>(){

 override fun initView(savedInstanceState: Bundle?) {

    }
   
    override fun createObserver() {
    }
   
}
    
    
"""

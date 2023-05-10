package other.mvvm.temparms.src.app_package

import temparms.ArmsPluginTemplateProviderImpl

fun armsFragment(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value}

import android.os.Bundle
import com.lovemaps.core.base.BaseMvvmFragment

import ${provider.appPackageName.value}.databinding.Fragment${provider.pageName.value}Binding
import ${provider.appPackageName.value}.${provider.pageName.value}ViewModel
import ${provider.appPackageName.value}.R

class ${provider.pageName.value}Fragment : BaseMvvmFragment<${provider.pageName.value}ViewModel,Fragment${provider.pageName.value}Binding>() {
    
    companion object {
        fun newInstance():${provider.pageName.value}Fragment {
            val fragment = ${provider.pageName.value}Fragment()
            return fragment
        }
    }
    
 override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {
    }

    override fun lazyLoadData() {

    }
}
    
    
"""
package other.mvvm.temparms.src.app_package

import temparms.ArmsPluginTemplateProviderImpl

fun armsFragment(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value}

import android.os.Bundle
import com.lovemaps.core.base.BaseMvvmFragment
import ${provider.appPackageName.value}.databinding.Fragment${provider.pageName.value}Binding
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ViewModel

class ${provider.pageName.value}Fragment : BaseMvvmFragment<${provider.pageName.value}ViewModel, Fragment${provider.pageName.value}Binding>() {

    companion object {
        fun newInstance(): ${provider.pageName.value}Fragment {
            return ${provider.pageName.value}Fragment()
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
package other.mvvm.temparms.src.app_package

import temparms.ArmsPluginTemplateProviderImpl

fun armsComposeFragment(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.pagePackageName.value}

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.lovemaps.core.base.BaseComposeFragment
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ViewModel

class ${provider.pageName.value}Fragment : BaseComposeFragment<${provider.pageName.value}ViewModel>() {

    companion object {
        fun newInstance(): ${provider.pageName.value}Fragment {
            return ${provider.pageName.value}Fragment()
        }
    }

    @Composable
    override fun InitComposeView() {
        // TODO: Add your Compose UI here
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {

    }

    override fun lazyLoadData() {

    }
}
"""

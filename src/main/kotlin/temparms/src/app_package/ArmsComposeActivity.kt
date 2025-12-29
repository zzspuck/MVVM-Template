package other.mvvm.temparms.src.app_package

import temparms.ArmsPluginTemplateProviderImpl

fun armsComposeActivity(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.pagePackageName.value}

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lovemaps.core.base.BaseComposeActivity
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ViewModel

class ${provider.pageName.value}Activity : BaseComposeActivity<${provider.pageName.value}ViewModel>() {

    @Composable
    override fun InitComposeView(modifier: Modifier) {
        // TODO: Add your Compose UI here
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {

    }
}
"""

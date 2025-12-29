package other.mvvm.temparms.src

import temparms.ArmsPluginTemplateProviderImpl

fun armsManifest(provider: ArmsPluginTemplateProviderImpl)= """
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application>
        <activity android:name="${provider.pagePackageName.value}.${provider.pageName.value}Activity"
            android:screenOrientation="portrait"
            />
    </application>
</manifest>
"""
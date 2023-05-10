package other.mvvm.temparms.src.app_package

//fun armsModel(provider: ArmsPluginTemplateProviderImpl) = """
//package ${provider.modelPackageName.value}
//
//class ${provider.pageName.value}Model
//constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), ${provider.pageName.value}Contract.Model{
//    @Inject
//    lateinit var mGson:Gson;
//    @Inject
//    lateinit var mApplication:Application;
//
//    override fun onDestroy() {
//          super.onDestroy();
//    }
//}
//"""
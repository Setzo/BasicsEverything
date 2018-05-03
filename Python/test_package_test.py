from test_package import ModuleClass, SubpackageModuleClass

print('works')

x = ModuleClass('a')
x.print()

y = SubpackageModuleClass()

# Requirements

Building the JBehave Eclipse Plugin requires:

* Eclipse SDK 3.7 (Indigo) or above,
* Java 1.6 or above
* Maven 3.0 or above 

Plugin dependencies are of two types:

* Eclipse Plugin dependencies (bundled with Eclipse SDK)
* External dependencies (retrieved via Maven):  can be for different scopes, e.g. runtime or test

Project classpath relies on the **plugin nature** of the project: 

* Eclipse plugin dependencies managed through the plugin nature and declared in `META-INF/MANIFEST.MF`
    
```
Require-Bundle: org.eclipse.ui,
 org.eclipse.core.runtime,
 org.eclipse.jface.text,
 org.eclipse.ui.editors,
 org.eclipse.jdt.core;bundle-version="3.3.1",
 ...
```

* External dependencies (retrieved through Maven) from the plugin point of view are declared in: `build.properties` and `META-INF/MANIFEST.MF`. 
Thus both files must be updated according to modified or added dependencies. Test dependencies can be found in the lib directory, 
but since they are not part of the `build.properties` nor `META-INF/MANIFEST.MF`, they are not exported with the plugin.

```
Bundle-ClassPath: .,
 lib/commons-collections-3.2.1.jar,
 lib/commons-io-2.1.jar,
 lib/commons-lang-2.5.jar,
 ...
```

Dependencies that are not part of the plugin executable (e.g. test scoped ones) must be manually added in the project dependencies:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.6"/>
	<classpathentry kind="con" path="org.eclipse.pde.core.requiredPlugins"/>
	<classpathentry kind="src" path="src"/>
	<classpathentry kind="src" path="test"/>
	<classpathentry kind="src" path="examples/user-account/src/main/java"/>
	<classpathentry kind="src" path="examples/user-account/src/main/story"/>
	<classpathentry kind="lib" path="lib/hamcrest-core-1.1.jar"/>
	<classpathentry kind="lib" path="lib/testng-6.3.1.jar"/>
	<classpathentry kind="lib" path="lib/hamcrest-integration-1.1.jar"/>
	<classpathentry kind="lib" path="lib/hamcrest-library-1.1.jar"/>
	<classpathentry kind="lib" path="lib/mockito-all-1.8.4.jar"/>
	<classpathentry kind="output" path="bin"/>
</classpath>
```

# To build the plugin

* Retrieve the external dependencies to the lib/ directory via Maven:
 
  mvn dependency:copy-dependencies

* Using Maven: 

  mvn clean install 
  
* Using Eclipse: 

  Right click on the plugin 
  Export...
  *Deployable plug-ins and fragments*
  "Next"
  Select the corresponding plugin 
  Define the wanted directory
  Click "Finish"

# To launch the plugin in an runtime workspace

* Run as... Eclipse Application, configure and select required plugin
* or right click on `Eclipse Workbench.launch` Run as...

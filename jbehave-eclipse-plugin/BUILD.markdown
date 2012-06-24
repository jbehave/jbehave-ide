# Requirements

Building the JBehave Eclipse Plugin requires:

* Eclipse SDK 3.7 (Indigo) or above,
* Java 1.6 or above
* Maven 3.0 or above 

Plugin dependencies are of two types:

* Eclipse Plugin dependencies (bundled with Eclipse SDK)
* External dependencies (retrieved via Maven):  can be for different scopes, e.g. runtime or test

Project classpath relies on the **plugin nature** of the project: 

* Eclipse plugin dependencies are declared in `META-INF/MANIFEST.MF`
    
```
Require-Bundle: org.eclipse.ui,
 org.eclipse.core.runtime,
 org.eclipse.jface.text,
 org.eclipse.ui.editors,
 org.eclipse.jdt.core;bundle-version="3.3.1",
 ...
```

* External dependencies (retrieved via Maven) are declared in the `META-INF/MANIFEST.MF` 

```
Bundle-ClassPath: .,
 lib/jbehave-core-3.6.7.jar,
 lib/commons-codec-1.6.jar,
 lib/commons-collections-3.2.1.jar,
 lib/commons-io-2.1.jar,
 lib/commons-lang-2.5.jar,
 ...
```

The Eclipse project Build Path is declared in the `.classpath` and will include non-runtime dependencies (e.g. compile and test scoped ones)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.6"/>
	<classpathentry kind="con" path="org.eclipse.pde.core.requiredPlugins"/>
	<classpathentry kind="src" path="src"/>
	<classpathentry kind="src" path="test"/>
	<classpathentry kind="src" path="examples/user-account/src/main/java"/>
	<classpathentry kind="src" path="examples/user-account/src/main/story"/>
	<classpathentry kind="lib" path="lib/commons-lang-2.5.jar"/>
	<classpathentry kind="lib" path="lib/commons-io-2.1.jar"/>
	<classpathentry kind="lib" path="lib/guava-10.0.1.jar"/>
	<classpathentry kind="lib" path="lib/functionaljava-3.0.jar"/>
	<classpathentry kind="lib" path="lib/junit-dep-4.8.2.jar"/>
	<classpathentry kind="lib" path="lib/hamcrest-core-1.1.jar"/>
	<classpathentry kind="lib" path="lib/hamcrest-integration-1.1.jar"/>
	<classpathentry kind="lib" path="lib/hamcrest-library-1.1.jar"/>
	<classpathentry kind="lib" path="lib/mockito-all-1.8.4.jar"/>
	<classpathentry kind="lib" path="lib/logback-classic-1.0.0.jar"/>
	<classpathentry kind="lib" path="lib/logback-core-1.0.0.jar"/>
	<classpathentry kind="lib" path="lib/slf4j-api-1.6.4.jar"/>
	<classpathentry kind="lib" path="lib/plexus-utils-2.0.5.jar"/>
	... 
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

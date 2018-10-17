# hook-auditor
auditor plugin to watch the many github plugins in jenkins and debug

## pre-requisites
java 8

```bash
# sdk man
sdk use java 8.0.181-zulu
```

## running

```bash
mvn install -DskipTests
mvn hpi:run
```

## updating versions of plugins

go to: http://localhost:8080/script or your jenkins instance script console

1. run this script
2. paste updates to pom.xml

```bash
println Jenkins.instance.getVersion()

def plugins = Jenkins.instance.pluginManager.plugins.toSorted()

// maven
plugins.each{
  plugin ->
  def attrs = plugin.getManifest().mainAttributes
//  println "${plugin.getInfo().name},${plugin.getVersion()}, ${attrs.getValue('Group-Id')}"
  println "<dependency>"
  println "\t<groupId>${attrs.getValue('Group-Id')}</groupId>"
  println "\t<artifactId>${plugin.getInfo().name}</artifactId>"
  println "\t<version>${plugin.getVersion()}</version>"
  println "</dependency>"
  println ""
}
```

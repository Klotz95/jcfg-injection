<h1>Java Configuration Injection</h1>
<p>jcfg-injection is a framework to easily inject configuration properties to classes. It accepts 
different configuration file formats like JSON or YAML. The architecture is designed to easily implement 
other configuration file mapper if necessary. <br>
To inject configuration files all you have to do is to use the @CofigInject-annotation. The annotation has to put above the 
method which is expecting the configuration file object. <strong>The project is currently work in progress and 
didn't have all requirements implemented</strong> </p>
<h2>Modules</h2> 
<p>The following table will give you an overview about the modules inside this project </p>
<table>
<tr>
<th>Module name</th>
<th>Packages</th>
<th>Module description</h2>
</tr>
<tr>
<td>jcfg-core</td>
<td><ul>
<li>api</li>
<li>excepions</li>
<li>mapper</li>
<li>storage</li></ul>
<td>This module contains all basic requirements like the definition of the <strong>API</strong>
and the definition of the required <strong>storages</strong>. It also contains the <strong>mapper</strong> which transform 
a configuration file to api objects. </td>
</tr>
<tr>
<td>jcfg-injection-manager (WIP)</td>
<td>None</td>
<td>This module will contain the implementations which handle the injection of the configuration objects 
to the classes using the @ConfigInject-annotation</td>
</tr>
</table>
<h2>API</h2>
<p>jcfg-injection offers a small data model to handle the injection of configuration documents<br>
The following table will give you an overview of the model. The data model classes doesn't have 
a relation to each other. </p>
<table>
<tr>
<th>Class name</th>
<th>Properties</th>
<th>Use cases</th>
</tr>
<tr>
<td>ConfigurationDocument</td>
<td><ul>
<li>documentName:String</li>
<li>configurationParameters:Map<String, Object></li>
</ul>
<td>This object is representing a plain configuration document which can be mapped
to a specified class. It contains the parameters inside a map which has as key a String 
defining the parameter-name and as value the related object.</td>
</tr>
<tr>
<td>ConfigurationFile</td>
<td><ul>
<li>configurationDocuments:Map<String,JsonNode></li>
<li>globalParameters:Map<String,String></li>
</ul>
<td>This class represents the file structure of the document saved to the specified configuration
storage. It holding the configurationDocuments inside a Map where the documentName
is represented by the key and the content holds as a JsonNode. The file is also
containing the global parameters which are accesable in the whole project</td>
</tr>
<tr>
<td>ConfigurationFileType</td>
<td><ul>
<li>INI</li>
<li>YAML</li>
<li>XML</li>
<li>TOML</li>
<li>JSON</li></ul>
</td>
<td>This enum defines the type in which the ConfigurationFile will be saved inside the 
specified storage.</td>
</tr>
</table>
<h2>Configuration File Mapper</h2>
<p>jcfg-injection is using the Jackson file mapper to convert the configuration files 
to API objects. To create a custom configuration file mapper all you have to do is to 
offer an implementation of the filemapper and implement a JsonFactory which is accepting 
the file-type.<br> If the file-type you want to support is a common file type and 
not a custom made file type i would recommend that you put the generated ConfigurationFileMapper
inside the jcfg-core module</p>
<h2>@ConfigInject-Annotation</h2>
<p>The ConfigInject-annotation can be used to mark a method as entry point of the 
jcfg-injection framework. The framework will use the marked method to inejct 
the configuration object. Via the use of the java-reflection-libary the type 
of the ConfigurationDocument-object will be find out. The method is allowed to just
have one parameter which is extending the ConfigurationDocument-class. 
</p>


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
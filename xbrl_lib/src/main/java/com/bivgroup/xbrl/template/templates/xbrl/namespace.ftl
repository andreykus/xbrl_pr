<#if namespaces?exists>
    <#foreach entry in namespaces.entrySet()>
        xmlns:${entry.key}="${entry.value}"
    </#foreach>
</#if>
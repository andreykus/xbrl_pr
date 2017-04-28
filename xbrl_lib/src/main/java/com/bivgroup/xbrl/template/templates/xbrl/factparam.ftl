<!-- FACT ELEMENTS -->
<#if elements?exists>
    <#foreach element in elements>
    <${element.getAlias()}:${element.getName()} contextRef="${element.getContext().getIdContext()}" id="${element.getIdElement()}"<#if element.getPrecision()??>   precision="${element.getPrecision()}"  </#if> unitRef="${element.getUnit().getIdUnit()}">${element.getValue()}</${element.getAlias()}:${element.getName()}>
    </#foreach>
</#if>
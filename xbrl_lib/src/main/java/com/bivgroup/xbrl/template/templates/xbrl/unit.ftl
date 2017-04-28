<!-- UNIT DIMENSION DEFINED -->
<#if units?exists>
    <#foreach unit in units>
        <xbrli:unit id="${unit.getIdUnit()}" xmlns:${unit.getUrl().getNameSpace()}="${unit.getUrl().getUrl()}">
            <xbrli:measure>${unit.getUrl().getNameSpace()}:${unit.getNameUnit()}</xbrli:measure>
        </xbrli:unit>
    </#foreach>
</#if>

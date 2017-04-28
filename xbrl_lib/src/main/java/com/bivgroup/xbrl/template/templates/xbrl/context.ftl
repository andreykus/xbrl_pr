<!-- CONTEXT DEFINED -->
<#if contexts?exists>
    <#foreach context in contexts>
    <xbrli:context id="${context.getIdContext()}">
        <xbrli:entity>
            <xbrli:identifier scheme="${context.getUrl()}">${context.getName()}</xbrli:identifier>
        </xbrli:entity>
        <xbrli:period>
            <#switch context.getType().name()>
                <#case "INSTANT">
                    <#assign InstantDate = context.getInstantDate()?date>
                    <xbrli:instant>${InstantDate?iso_utc}</xbrli:instant>
                    <#break>
                <#case "DURATION">
                    <#assign StartDate = context.getStartDate()?date>
                    <#assign EndDate = context.getEndDate()?date>
                    <xbrli:startDate>${StartDate?string["yyyy-MM-dd"]}</xbrli:startDate>
                    <xbrli:endDate>${EndDate?iso_utc}</xbrli:endDate>
                    <#break>
                <#default>
                    <forever/>
            </#switch>
        </xbrli:period>
    </xbrli:context>
    </#foreach>
</#if>
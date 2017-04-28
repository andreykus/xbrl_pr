<#assign value = "">
<#if value = ''>
<#else>
</#if>

	
<?xml version="1.0" encoding="UTF-8"?>
<xbrl xmlns="http://www.xbrl.org/2003/instance"
    xmlns:link="http://www.xbrl.org/2003/linkbase"
    xmlns:xbrli="http://www.xbrl.org/2003/instance"
    xmlns:xhtml="http://www.w3.org/1999/xhtml"
    xmlns:xlink="http://www.w3.org/1999/xlink"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	<#include "namespace.ftl"/>
	xsi:schemaLocation="${shemalocation}">
    <!-- Generated ${date} ${module} ${version} -->
	
	<!-- TAXONOMIA DEFINED -->
	<link:schemaRef
        xlink:href="${shemaref}" xlink:type="simple"/>
    

	<#include "context.ftl"/>

	<#include "unit.ftl"/>

    <#include "factparam.ftl"/>
</xbrl>

<!-- The contents of this file are subject to the BIV License Version 1.0 (the "License"); 
	you may not use this file except in compliance with the License. 
	Software distributed under the License is distributed on an "AS IS" basis,
	WITHOUT WARRANTY OF ANY KIND, either express or implied. 
	Copyright (c) BIV, Inc., 2017-${date}.
	All Rights Reserved.
-->

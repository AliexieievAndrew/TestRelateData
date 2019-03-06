<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h>Your file has been successfully uploaded</h><br>
<h2>please select a range and press submit to generate charts</h2><br>
<#if rage?has_content>
    <h2>Date's Rage of this file:</h2>
    <h2>${rage}</h2><br>
</#if>
<form method="GET" action="/process" enctype="multipart/form-data">
    from <input type="datetime-local" value="2018-01-01T00:00" name="timeFrom"/><br/><br/>
    to <input type="datetime-local" value="2019-01-01T00:00" name="timeTo"/><br/><br/>
    <input type="submit" value="Submit"/>
</form>
<#if top10DestinationAddress?has_content>
    <h2>top 10 Destination Address</h2><br>
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>count</th>
        </tr>
        </thead>
        <tbody>
        <#list top10DestinationAddress as key,value >
            <tr>
                <#if key?has_content>
                    <td>${key}</td>
                <#else >
                    <td>EMPTY DATA (was null)</td>
                </#if>
                <td>${value}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>

<#if top10SourceAddress?has_content>
    <h2>top 10 Source Address</h2><br>
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>count</th>
        </tr>
        </thead>
        <tbody>
        <#list top10SourceAddress as key,value >
            <tr>
                <#if key?has_content>
                    <td>${key}</td>
                <#else >
                    <td>EMPTY DATA (was null)</td>
                </#if>
                <td>${value}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>

<#if top3ProtocolNumber?has_content>
    <h2>top 3 Protocol Number</h2><br>
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>count</th>
        </tr>
        </thead>
        <tbody>
        <#list top3ProtocolNumber as key,value >
            <tr>
                <#if key?has_content>
                    <td>${key}</td>
                <#else >
                    <td>EMPTY DATA (was null)</td>
                </#if>
                <td>${value}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>

<#if top10ApplicationName?has_content>
    <h2>top 10 Application Name</h2><br>
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>count</th>
        </tr>
        </thead>
        <tbody>
        <#list top10ApplicationName as key,value >
            <tr>
                <#if key?has_content>
                    <td>${key}</td>
                <#else >
                    <td>EMPTY DATA (was null)</td>
                </#if>
                <td>${value}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>

</body>
</html>
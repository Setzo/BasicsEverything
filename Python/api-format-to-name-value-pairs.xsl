<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>
    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="ChildrenCount"/>
    <xsl:template match="RelatedEntities"/>
    <xsl:template match="singleElementCollection"/>

    <xsl:template match="Fields">
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="Field">
        <xsl:element name="{@Name}">
            <xsl:copy-of select="Value/text()"/>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>

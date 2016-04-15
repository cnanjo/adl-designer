package org.openehr.bmm;

/**
 * Created by cnanjo on 4/11/16.
 */
public abstract class BmmDefinitions {

    private String bmmInternalVersion;
    private String schemaNameDelimiter = "::";
    private String packageNameDelimiter = ".";
    private String bmmSchemeFileExtension = ".bmm";

    public String getBmmInternalVersion() {
        return bmmInternalVersion;
    }

    public void setBmmInternalVersion(String bmmInternalVersion) {
        this.bmmInternalVersion = bmmInternalVersion;
    }

    public String getSchemaNameDelimiter() {
        return schemaNameDelimiter;
    }

    public void setSchemaNameDelimiter(String schemaNameDelimiter) {
        this.schemaNameDelimiter = schemaNameDelimiter;
    }

    public String getPackageNameDelimiter() {
        return packageNameDelimiter;
    }

    public void setPackageNameDelimiter(String packageNameDelimiter) {
        this.packageNameDelimiter = packageNameDelimiter;
    }

    public String getBmmSchemeFileExtension() {
        return bmmSchemeFileExtension;
    }

    public void setBmmSchemeFileExtension(String bmmSchemeFileExtension) {
        this.bmmSchemeFileExtension = bmmSchemeFileExtension;
    }
}

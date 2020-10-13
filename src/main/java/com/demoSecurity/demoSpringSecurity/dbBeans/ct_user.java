package com.demoSecurity.demoSpringSecurity.dbBeans;

public class ct_user {
    private Long id;
    private String name;
    private String password;
    private String fullName;
    private String extension;
    private Long webadminrole;
    private Long usergroupid;
    private Long domainId;

    public Long getWebadminrole() {
        return webadminrole;
    }

    public void setWebadminrole(Long webadminrole) {
        this.webadminrole = webadminrole;
    }

    public Long getUsergroupid() {
        return usergroupid;
    }

    public void setUsergroupid(Long usergroupid) {
        this.usergroupid = usergroupid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String toString() {
        return "{" + id + "," + name + "," + password + "," + domainId + "}";
    }

}

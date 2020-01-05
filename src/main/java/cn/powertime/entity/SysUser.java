package cn.powertime.entity;

public class SysUser {

    private String id;

    private Integer type;

    private String username;

    private String account;

    private String nickname;

    private String email;

    private String phone;

    private Integer sex;

    private String avatar;

    private String password;

    private String pwdSalt;

    private  String modPasswdDate;
    private Integer multipoint;

    private Integer level;

    private Integer score;
    private Integer expert;

    private Integer del;

    private  String introduction;
    private Integer status;


    private String createTime;

    private String  updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getSex() {
        return sex;
    }


    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPwdSalt() {
        return pwdSalt;
    }

    public void setPwdSalt(String pwdSalt) {
        this.pwdSalt = pwdSalt == null ? null : pwdSalt.trim();
    }

    public Integer getMultipoint() {
        return multipoint;
    }


    public void setMultipoint(Integer multipoint) {
        this.multipoint = multipoint;
    }

    public Integer getLevel() {
        return level;
    }


    public void setLevel(Integer level) {
        this.level = level;
    }


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    public Integer getExpert() {
        return expert;
    }


    public void setExpert(Integer expert) {
        this.expert = expert;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getModPasswdDate() {
        return modPasswdDate;
    }

    public void setModPasswdDate(String modPasswdDate) {
        this.modPasswdDate = modPasswdDate;
    }
}
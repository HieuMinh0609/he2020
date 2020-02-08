package com.hieuminh.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    private static final long serialVersionUID = 496111208079841440L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[iduser]")
    private Integer idUser;

    @ManyToOne
    @JoinColumn(name = "[idrole]")
    private RoleEntity idRoleEntity;


    @OneToOne(mappedBy ="userEntityLocation" ,fetch = FetchType.LAZY)
    private LocationEntity locationEntities;

    @OneToMany(mappedBy ="idUserEntity" ,fetch = FetchType.LAZY)
    private List<BillEntity> billEntityList;

    @OneToMany(mappedBy ="transUserEntity" ,fetch = FetchType.LAZY)
    private List<TransporterEntity> transporterEntities;


    @OneToMany(mappedBy ="idHistoryUserEntity" ,fetch = FetchType.LAZY)
    private List<MyHistoryEntity> historyEntity;

    @OneToMany(mappedBy ="idLoginUserEntity" ,fetch = FetchType.LAZY)
    private List<LoginEntity> loginEntityList;

    @OneToMany(mappedBy ="idUserFeedBack" ,fetch = FetchType.LAZY)
    private List<FeedBackEntity> feedBackEntityList;

    @OneToMany(mappedBy ="idUserEntity" ,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;



    @Column(name = "[place]")
    private String place;


    @Column(name = "[namefull]")
    private String nameFull;


    @Column(name = "[sex]")
    private Integer sex;

    @Column(name = "[cardid]")
    private String cardId;

    @Column(name = "[email]")
    private String email;

    @Column(name = "[phonenumber]")
    private String phoneNumber;

    @Column(name = "[block]")
    private Integer block;





    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public RoleEntity getIdRoleEntity() {
        return idRoleEntity;
    }

    public void setIdRoleEntity(RoleEntity idRoleEntity) {
        this.idRoleEntity = idRoleEntity;
    }



    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }



    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<BillEntity> getBillEntityList() {
        return billEntityList;
    }

    public void setBillEntityList(List<BillEntity> billEntityList) {
        this.billEntityList = billEntityList;
    }

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }
    public void setLoginEntityList(List<LoginEntity> loginEntityList) {
        this.loginEntityList = loginEntityList;
    }
    public List<LoginEntity> getLoginEntityList() {
        return loginEntityList;
    }


    public List<FeedBackEntity> getFeedBackEntityList() {
        return feedBackEntityList;
    }

    public void setFeedBackEntityList(List<FeedBackEntity> feedBackEntityList) {
        this.feedBackEntityList = feedBackEntityList;
    }


    public List<MyHistoryEntity> getHistoryEntity() {
        return historyEntity;
    }

    public void setHistoryEntity(List<MyHistoryEntity> historyEntity) {
        this.historyEntity = historyEntity;
    }

    public List<TransporterEntity> getTransporterEntities() {
        return transporterEntities;
    }

    public void setTransporterEntities(List<TransporterEntity> transporterEntities) {
        this.transporterEntities = transporterEntities;
    }

    public LocationEntity getLocationEntities() {
        return locationEntities;
    }

    public void setLocationEntities(LocationEntity locationEntities) {
        this.locationEntities = locationEntities;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }
}

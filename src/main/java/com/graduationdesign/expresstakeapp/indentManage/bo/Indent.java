package com.graduationdesign.expresstakeapp.indentManage.bo;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Scope("prototype")
public class Indent {
    @Id
    @GeneratedValue
    private int indentId;
    private int indentCode;
    private int indentWealth;
    private int expressId;
    private int expressType;
    private int expressSize;
    private String expressAddress;
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private Date sendIndentTime;
    private int indentState;
    private String receiverName;
    private String receiverPhone;
    private Date receiveIndentTime;
    private Date predictArriveTime;
    private Date completeIndentTime;
    private int isExitBySender;
    private int IsExitByReceiver;


    public int getIndentId() {
        return indentId;
    }

    public void setIndentId(int indentId) {
        this.indentId = indentId;
    }

    public int getIndentCode() {
        return indentCode;
    }

    public void setIndentCode(int indentCode) {
        this.indentCode = indentCode;
    }

    public int getIndentWealth() {
        return indentWealth;
    }

    public void setIndentWealth(int indentWealth) {
        this.indentWealth = indentWealth;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public int getExpressType() {
        return expressType;
    }

    public void setExpressType(int expressType) {
        this.expressType = expressType;
    }

    public int getExpressSize() {
        return expressSize;
    }

    public void setExpressSize(int expressSize) {
        this.expressSize = expressSize;
    }

    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress;
    }

    public Date getSendIndentTime() {
        return sendIndentTime;
    }

    public void setSendIndentTime(Date sendIndentTime) {
        this.sendIndentTime = sendIndentTime;
    }

    public int getIndentState() {
        return indentState;
    }

    public void setIndentState(int indentState) {
        this.indentState = indentState;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public Date getReceiveIndentTime() {
        return receiveIndentTime;
    }

    public void setReceiveIndentTime(Date receiveIndentTime) {
        this.receiveIndentTime = receiveIndentTime;
    }

    public Date getPredictArriveTime() {
        return predictArriveTime;
    }

    public void setPredictArriveTime(Date predictArriveTime) {
        this.predictArriveTime = predictArriveTime;
    }

    public Date getCompleteIndentTime() {
        return completeIndentTime;
    }

    public void setCompleteIndentTime(Date completeIndentTime) {
        this.completeIndentTime = completeIndentTime;
    }

    public int getIsExitBySender() {
        return isExitBySender;
    }

    public void setIsExitBySender(int isExitBySender) {
        this.isExitBySender = isExitBySender;
    }

    public int getIsExitByReceiver() {
        return IsExitByReceiver;
    }

    public void setIsExitByReceiver(int isExitByReceiver) {
        IsExitByReceiver = isExitByReceiver;
    }



    @Override
    public String toString() {
        return "Indent{" +
                "indentId=" + indentId +
                ", indentCode=" + indentCode +
                ", indentWealth=" + indentWealth +
                ", senderName='" + senderName + '\'' +
                ", senderPhone='" + senderPhone + '\'' +
                ", senderAddress='" + senderAddress + '\'' +
                ", expressId=" + expressId +
                ", expressType=" + expressType +
                ", expressSize=" + expressSize +
                ", expressAddress='" + expressAddress + '\'' +
                ", sendIndentTime=" + sendIndentTime +
                ", indentState=" + indentState +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiveIndentTime=" + receiveIndentTime +
                ", predictArriveTime=" + predictArriveTime +
                ", completeIndentTime=" + completeIndentTime +
                '}';
    }
}

package com.origicheck.africababa.sync.worker;

/**
 * Created by victor on 10/17/2015.
 */
public class FormData {
    private String formDataBuilder = "";

    public FormData() {
        // TODO Auto-generated constructor stub
    }

    public void startBuildingFormData() {
        setFormDataBuilder("");
    }

    public String getFormDataBuilder() {
        return formDataBuilder;
    }

    public void setFormDataBuilder(String formDataBuilder) {
        this.formDataBuilder = formDataBuilder;
    }

    public String appendFormData(String key, String value) {

        if (getFormDataBuilder().equals("")) {
            formDataBuilder = key + "=" + value;
        } else {
            formDataBuilder += "&" + key + "=" + value;
        }
        return getData();
    }

    public String getData() {
        // TODO Auto-generated method stub
        return formDataBuilder;
    }
}

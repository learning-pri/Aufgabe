package com.eko.task.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommonRequest {

  private String actionName;

  private Integer actionTypeId;

  private String requesterCell;

  private boolean globalAccess;

  private Parameters parameters;

  public String getActionName() {

    return actionName;
  }

  public void setActionName(String actionName) {

    this.actionName = actionName;
  }

  public Integer getActionTypeId() {

    return actionTypeId;
  }


  public void setActionTypeId(Integer actionTypeId) {

    this.actionTypeId = actionTypeId;
  }


  public boolean isGlobalAccess() {

    return globalAccess;
  }

  public String getRequesterCell() {

    return requesterCell;
  }

  public void setRequesterCell(String requesterCell) {

    this.requesterCell = requesterCell;
  }

  public boolean hasGlobalAccess() {

    return globalAccess;
  }

  public void setGlobalAccess(boolean globalAccess) {

    this.globalAccess = globalAccess;
  }


  public Parameters getParameters() {

    return parameters;
  }


  public void setParameters(Parameters parameters) {

    this.parameters = parameters;
  }

}

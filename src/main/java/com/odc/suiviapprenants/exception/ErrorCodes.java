package com.odc.suiviapprenants.exception;

public enum ErrorCodes {

  ROLE_NOT_FOUND(1000),
  ROLE_NOT_VALID(1001),
  ROLE_ALREADY_IN_USE(1002),

  ADMIN_NOT_FOUND(2000),
  ADMIN_NOT_VALID(2001),
  ADMIN_ALREADY_IN_USE(2002),

  APPRENANT_NOT_FOUND(3000),
  APPRENANT_NOT_VALID(3001),
  APPRENANT_ALREADY_IN_USE(3002),

  GROUPE_TAG_NOT_FOUND(4000),
  GROUPE_TAG_NOT_VALID(4001),
  GROUPE_TAG_ALREADY_IN_USE(4002),

  TAG_NOT_FOUND(5000),
  TAG_NOT_VALID(5001),
  TAG_ALREADY_IN_USE(5002),

  BAD_CREDENTIALS(12003),
  ;

  private int code;

  ErrorCodes(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}

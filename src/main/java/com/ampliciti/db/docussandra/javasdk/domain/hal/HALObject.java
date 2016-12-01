/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.ampliciti.db.docussandra.javasdk.domain.hal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class HALObject {

  @JsonProperty
  private Collections collections;

  @JsonProperty
  private Self self;

  @JsonProperty
  private Up up;

  /**
   * @return the collections
   */
  public Collections getCollections() {
    return collections;
  }

  /**
   * @param collections the collections to set
   */
  public void setCollections(Collections collections) {
    this.collections = collections;
  }

  /**
   * @return the self
   */
  public Self getSelf() {
    return self;
  }

  /**
   * @param self the self to set
   */
  public void setSelf(Self self) {
    this.self = self;
  }

  /**
   * @return the up
   */
  public Up getUp() {
    return up;
  }

  /**
   * @param up the up to set
   */
  public void setUp(Up up) {
    this.up = up;
  }
}

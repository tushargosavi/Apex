package com.datatorrent.api;

import org.apache.hadoop.conf.Configuration;

public interface Module
{
  /**
   * Specify sub-dag of the module. By default this method will be called
   * when module is added into the DAG.
   * To support modules which does expansion based on the it's properties,
   * this method can be annotated with Asynchronous annotation and platform
   * will call the method after properties of the modules are injected and
   * before physical plan is generated.
   *
   * @param dag
   * @param conf
   */
  public void populateDAG(DAG dag, Configuration conf);
}

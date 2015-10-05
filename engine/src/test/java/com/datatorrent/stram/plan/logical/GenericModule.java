package com.datatorrent.stram.plan.logical;

import com.datatorrent.api.DAG;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.Module;
import com.datatorrent.api.annotation.OutputPortFieldAnnotation;
import com.datatorrent.stram.engine.GenericOperatorProperty;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericModule implements Module
{

  private static final Logger LOG = LoggerFactory.getLogger(GenericModule.class);


  public volatile Object inport1Tuple = null;


  @OutputPortFieldAnnotation(optional = true) final public transient DefaultOutputPort<Object> outport1 = new DefaultOutputPort<Object>();

  @OutputPortFieldAnnotation(optional = true) final public transient DefaultOutputPort<Object> outport2 = new DefaultOutputPort<Object>();

  private String emitFormat;

  public boolean booleanProperty;

  private String myStringProperty;

  private transient GenericOperatorProperty genericOperatorProperty = new GenericOperatorProperty("test");

  public String getMyStringProperty()
  {
    return myStringProperty;
  }

  public void setMyStringProperty(String myStringProperty)
  {
    this.myStringProperty = myStringProperty;
  }

  public boolean isBooleanProperty()
  {
    return booleanProperty;
  }

  public void setBooleanProperty(boolean booleanProperty)
  {
    this.booleanProperty = booleanProperty;
  }

  public String propertySetterOnly;

  /**
   * setter w/o getter defined
   *
   * @param v
   */
  public void setStringPropertySetterOnly(String v)
  {
    this.propertySetterOnly = v;
  }

  public String getEmitFormat()
  {
    return emitFormat;
  }

  public void setEmitFormat(String emitFormat)
  {
    this.emitFormat = emitFormat;
  }

  public GenericOperatorProperty getGenericOperatorProperty()
  {
    return genericOperatorProperty;
  }

  public void setGenericOperatorProperty(GenericOperatorProperty genericOperatorProperty)
  {
    this.genericOperatorProperty = genericOperatorProperty;
  }

  private void processInternal(Object o)
  {
    LOG.debug("Got some work: " + o);
    if (emitFormat != null) {
      o = String.format(emitFormat, o);
    }
    if (outport1.isConnected()) {
      outport1.emit(o);
    }
  }

  @Override public void populateDAG(DAG dag, Configuration conf)
  {
    LOG.info("populateDAG of module called");
  }
}

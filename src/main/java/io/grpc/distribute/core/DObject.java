// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: d-concurrent.proto

package io.grpc.distribute.core;

/**
 * Protobuf type {@code dconcurrent.DObject}
 */
public  final class DObject extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:dconcurrent.DObject)
    DObjectOrBuilder {
  // Use DObject.newBuilder() to construct.
  private DObject(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DObject() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private DObject(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            com.google.protobuf.Any.Builder subBuilder = null;
            if (className_ != null) {
              subBuilder = className_.toBuilder();
            }
            className_ = input.readMessage(com.google.protobuf.Any.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(className_);
              className_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            com.google.protobuf.Any.Builder subBuilder = null;
            if (metaParam_ != null) {
              subBuilder = metaParam_.toBuilder();
            }
            metaParam_ = input.readMessage(com.google.protobuf.Any.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(metaParam_);
              metaParam_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            com.google.protobuf.Any.Builder subBuilder = null;
            if (metaParamClass_ != null) {
              subBuilder = metaParamClass_.toBuilder();
            }
            metaParamClass_ = input.readMessage(com.google.protobuf.Any.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(metaParamClass_);
              metaParamClass_ = subBuilder.buildPartial();
            }

            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return DConcurrent.internal_static_dconcurrent_DObject_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return DConcurrent.internal_static_dconcurrent_DObject_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            DObject.class, Builder.class);
  }

  public static final int CLASSNAME_FIELD_NUMBER = 1;
  private com.google.protobuf.Any className_;
  /**
   * <code>.google.protobuf.Any className = 1;</code>
   */
  public boolean hasClassName() {
    return className_ != null;
  }
  /**
   * <code>.google.protobuf.Any className = 1;</code>
   */
  public com.google.protobuf.Any getClassName() {
    return className_ == null ? com.google.protobuf.Any.getDefaultInstance() : className_;
  }
  /**
   * <code>.google.protobuf.Any className = 1;</code>
   */
  public com.google.protobuf.AnyOrBuilder getClassNameOrBuilder() {
    return getClassName();
  }

  public static final int METAPARAM_FIELD_NUMBER = 2;
  private com.google.protobuf.Any metaParam_;
  /**
   * <code>.google.protobuf.Any metaParam = 2;</code>
   */
  public boolean hasMetaParam() {
    return metaParam_ != null;
  }
  /**
   * <code>.google.protobuf.Any metaParam = 2;</code>
   */
  public com.google.protobuf.Any getMetaParam() {
    return metaParam_ == null ? com.google.protobuf.Any.getDefaultInstance() : metaParam_;
  }
  /**
   * <code>.google.protobuf.Any metaParam = 2;</code>
   */
  public com.google.protobuf.AnyOrBuilder getMetaParamOrBuilder() {
    return getMetaParam();
  }

  public static final int METAPARAMCLASS_FIELD_NUMBER = 3;
  private com.google.protobuf.Any metaParamClass_;
  /**
   * <code>.google.protobuf.Any metaParamClass = 3;</code>
   */
  public boolean hasMetaParamClass() {
    return metaParamClass_ != null;
  }
  /**
   * <code>.google.protobuf.Any metaParamClass = 3;</code>
   */
  public com.google.protobuf.Any getMetaParamClass() {
    return metaParamClass_ == null ? com.google.protobuf.Any.getDefaultInstance() : metaParamClass_;
  }
  /**
   * <code>.google.protobuf.Any metaParamClass = 3;</code>
   */
  public com.google.protobuf.AnyOrBuilder getMetaParamClassOrBuilder() {
    return getMetaParamClass();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (className_ != null) {
      output.writeMessage(1, getClassName());
    }
    if (metaParam_ != null) {
      output.writeMessage(2, getMetaParam());
    }
    if (metaParamClass_ != null) {
      output.writeMessage(3, getMetaParamClass());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (className_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getClassName());
    }
    if (metaParam_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getMetaParam());
    }
    if (metaParamClass_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getMetaParamClass());
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof DObject)) {
      return super.equals(obj);
    }
    DObject other = (DObject) obj;

    boolean result = true;
    result = result && (hasClassName() == other.hasClassName());
    if (hasClassName()) {
      result = result && getClassName()
          .equals(other.getClassName());
    }
    result = result && (hasMetaParam() == other.hasMetaParam());
    if (hasMetaParam()) {
      result = result && getMetaParam()
          .equals(other.getMetaParam());
    }
    result = result && (hasMetaParamClass() == other.hasMetaParamClass());
    if (hasMetaParamClass()) {
      result = result && getMetaParamClass()
          .equals(other.getMetaParamClass());
    }
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasClassName()) {
      hash = (37 * hash) + CLASSNAME_FIELD_NUMBER;
      hash = (53 * hash) + getClassName().hashCode();
    }
    if (hasMetaParam()) {
      hash = (37 * hash) + METAPARAM_FIELD_NUMBER;
      hash = (53 * hash) + getMetaParam().hashCode();
    }
    if (hasMetaParamClass()) {
      hash = (37 * hash) + METAPARAMCLASS_FIELD_NUMBER;
      hash = (53 * hash) + getMetaParamClass().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static DObject parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static DObject parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static DObject parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static DObject parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static DObject parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static DObject parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static DObject parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static DObject parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static DObject parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static DObject parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static DObject parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static DObject parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(DObject prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code dconcurrent.DObject}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:dconcurrent.DObject)
          DObjectOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return DConcurrent.internal_static_dconcurrent_DObject_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return DConcurrent.internal_static_dconcurrent_DObject_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              DObject.class, Builder.class);
    }

    // Construct using io.grpc.distribute.DObject.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      if (classNameBuilder_ == null) {
        className_ = null;
      } else {
        className_ = null;
        classNameBuilder_ = null;
      }
      if (metaParamBuilder_ == null) {
        metaParam_ = null;
      } else {
        metaParam_ = null;
        metaParamBuilder_ = null;
      }
      if (metaParamClassBuilder_ == null) {
        metaParamClass_ = null;
      } else {
        metaParamClass_ = null;
        metaParamClassBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return DConcurrent.internal_static_dconcurrent_DObject_descriptor;
    }

    public DObject getDefaultInstanceForType() {
      return DObject.getDefaultInstance();
    }

    public DObject build() {
      DObject result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public DObject buildPartial() {
      DObject result = new DObject(this);
      if (classNameBuilder_ == null) {
        result.className_ = className_;
      } else {
        result.className_ = classNameBuilder_.build();
      }
      if (metaParamBuilder_ == null) {
        result.metaParam_ = metaParam_;
      } else {
        result.metaParam_ = metaParamBuilder_.build();
      }
      if (metaParamClassBuilder_ == null) {
        result.metaParamClass_ = metaParamClass_;
      } else {
        result.metaParamClass_ = metaParamClassBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof DObject) {
        return mergeFrom((DObject)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(DObject other) {
      if (other == DObject.getDefaultInstance()) return this;
      if (other.hasClassName()) {
        mergeClassName(other.getClassName());
      }
      if (other.hasMetaParam()) {
        mergeMetaParam(other.getMetaParam());
      }
      if (other.hasMetaParamClass()) {
        mergeMetaParamClass(other.getMetaParamClass());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      DObject parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (DObject) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.protobuf.Any className_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> classNameBuilder_;
    /**
     * <code>.google.protobuf.Any className = 1;</code>
     */
    public boolean hasClassName() {
      return classNameBuilder_ != null || className_ != null;
    }
    /**
     * <code>.google.protobuf.Any className = 1;</code>
     */
    public com.google.protobuf.Any getClassName() {
      if (classNameBuilder_ == null) {
        return className_ == null ? com.google.protobuf.Any.getDefaultInstance() : className_;
      } else {
        return classNameBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Any className = 1;</code>
     */
    public Builder setClassName(com.google.protobuf.Any value) {
      if (classNameBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        className_ = value;
        onChanged();
      } else {
        classNameBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any className = 1;</code>
     */
    public Builder setClassName(
        com.google.protobuf.Any.Builder builderForValue) {
      if (classNameBuilder_ == null) {
        className_ = builderForValue.build();
        onChanged();
      } else {
        classNameBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any className = 1;</code>
     */
    public Builder mergeClassName(com.google.protobuf.Any value) {
      if (classNameBuilder_ == null) {
        if (className_ != null) {
          className_ =
            com.google.protobuf.Any.newBuilder(className_).mergeFrom(value).buildPartial();
        } else {
          className_ = value;
        }
        onChanged();
      } else {
        classNameBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any className = 1;</code>
     */
    public Builder clearClassName() {
      if (classNameBuilder_ == null) {
        className_ = null;
        onChanged();
      } else {
        className_ = null;
        classNameBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any className = 1;</code>
     */
    public com.google.protobuf.Any.Builder getClassNameBuilder() {
      
      onChanged();
      return getClassNameFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Any className = 1;</code>
     */
    public com.google.protobuf.AnyOrBuilder getClassNameOrBuilder() {
      if (classNameBuilder_ != null) {
        return classNameBuilder_.getMessageOrBuilder();
      } else {
        return className_ == null ?
            com.google.protobuf.Any.getDefaultInstance() : className_;
      }
    }
    /**
     * <code>.google.protobuf.Any className = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> 
        getClassNameFieldBuilder() {
      if (classNameBuilder_ == null) {
        classNameBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder>(
                getClassName(),
                getParentForChildren(),
                isClean());
        className_ = null;
      }
      return classNameBuilder_;
    }

    private com.google.protobuf.Any metaParam_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> metaParamBuilder_;
    /**
     * <code>.google.protobuf.Any metaParam = 2;</code>
     */
    public boolean hasMetaParam() {
      return metaParamBuilder_ != null || metaParam_ != null;
    }
    /**
     * <code>.google.protobuf.Any metaParam = 2;</code>
     */
    public com.google.protobuf.Any getMetaParam() {
      if (metaParamBuilder_ == null) {
        return metaParam_ == null ? com.google.protobuf.Any.getDefaultInstance() : metaParam_;
      } else {
        return metaParamBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Any metaParam = 2;</code>
     */
    public Builder setMetaParam(com.google.protobuf.Any value) {
      if (metaParamBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        metaParam_ = value;
        onChanged();
      } else {
        metaParamBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any metaParam = 2;</code>
     */
    public Builder setMetaParam(
        com.google.protobuf.Any.Builder builderForValue) {
      if (metaParamBuilder_ == null) {
        metaParam_ = builderForValue.build();
        onChanged();
      } else {
        metaParamBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any metaParam = 2;</code>
     */
    public Builder mergeMetaParam(com.google.protobuf.Any value) {
      if (metaParamBuilder_ == null) {
        if (metaParam_ != null) {
          metaParam_ =
            com.google.protobuf.Any.newBuilder(metaParam_).mergeFrom(value).buildPartial();
        } else {
          metaParam_ = value;
        }
        onChanged();
      } else {
        metaParamBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any metaParam = 2;</code>
     */
    public Builder clearMetaParam() {
      if (metaParamBuilder_ == null) {
        metaParam_ = null;
        onChanged();
      } else {
        metaParam_ = null;
        metaParamBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any metaParam = 2;</code>
     */
    public com.google.protobuf.Any.Builder getMetaParamBuilder() {
      
      onChanged();
      return getMetaParamFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Any metaParam = 2;</code>
     */
    public com.google.protobuf.AnyOrBuilder getMetaParamOrBuilder() {
      if (metaParamBuilder_ != null) {
        return metaParamBuilder_.getMessageOrBuilder();
      } else {
        return metaParam_ == null ?
            com.google.protobuf.Any.getDefaultInstance() : metaParam_;
      }
    }
    /**
     * <code>.google.protobuf.Any metaParam = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> 
        getMetaParamFieldBuilder() {
      if (metaParamBuilder_ == null) {
        metaParamBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder>(
                getMetaParam(),
                getParentForChildren(),
                isClean());
        metaParam_ = null;
      }
      return metaParamBuilder_;
    }

    private com.google.protobuf.Any metaParamClass_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> metaParamClassBuilder_;
    /**
     * <code>.google.protobuf.Any metaParamClass = 3;</code>
     */
    public boolean hasMetaParamClass() {
      return metaParamClassBuilder_ != null || metaParamClass_ != null;
    }
    /**
     * <code>.google.protobuf.Any metaParamClass = 3;</code>
     */
    public com.google.protobuf.Any getMetaParamClass() {
      if (metaParamClassBuilder_ == null) {
        return metaParamClass_ == null ? com.google.protobuf.Any.getDefaultInstance() : metaParamClass_;
      } else {
        return metaParamClassBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Any metaParamClass = 3;</code>
     */
    public Builder setMetaParamClass(com.google.protobuf.Any value) {
      if (metaParamClassBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        metaParamClass_ = value;
        onChanged();
      } else {
        metaParamClassBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any metaParamClass = 3;</code>
     */
    public Builder setMetaParamClass(
        com.google.protobuf.Any.Builder builderForValue) {
      if (metaParamClassBuilder_ == null) {
        metaParamClass_ = builderForValue.build();
        onChanged();
      } else {
        metaParamClassBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any metaParamClass = 3;</code>
     */
    public Builder mergeMetaParamClass(com.google.protobuf.Any value) {
      if (metaParamClassBuilder_ == null) {
        if (metaParamClass_ != null) {
          metaParamClass_ =
            com.google.protobuf.Any.newBuilder(metaParamClass_).mergeFrom(value).buildPartial();
        } else {
          metaParamClass_ = value;
        }
        onChanged();
      } else {
        metaParamClassBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any metaParamClass = 3;</code>
     */
    public Builder clearMetaParamClass() {
      if (metaParamClassBuilder_ == null) {
        metaParamClass_ = null;
        onChanged();
      } else {
        metaParamClass_ = null;
        metaParamClassBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any metaParamClass = 3;</code>
     */
    public com.google.protobuf.Any.Builder getMetaParamClassBuilder() {
      
      onChanged();
      return getMetaParamClassFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Any metaParamClass = 3;</code>
     */
    public com.google.protobuf.AnyOrBuilder getMetaParamClassOrBuilder() {
      if (metaParamClassBuilder_ != null) {
        return metaParamClassBuilder_.getMessageOrBuilder();
      } else {
        return metaParamClass_ == null ?
            com.google.protobuf.Any.getDefaultInstance() : metaParamClass_;
      }
    }
    /**
     * <code>.google.protobuf.Any metaParamClass = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> 
        getMetaParamClassFieldBuilder() {
      if (metaParamClassBuilder_ == null) {
        metaParamClassBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder>(
                getMetaParamClass(),
                getParentForChildren(),
                isClean());
        metaParamClass_ = null;
      }
      return metaParamClassBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:dconcurrent.DObject)
  }

  // @@protoc_insertion_point(class_scope:dconcurrent.DObject)
  private static final DObject DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new DObject();
  }

  public static DObject getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DObject>
      PARSER = new com.google.protobuf.AbstractParser<DObject>() {
    public DObject parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new DObject(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DObject> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<DObject> getParserForType() {
    return PARSER;
  }

  public DObject getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}


/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.core;

public final class UserMessage {
    private UserMessage() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public interface LoginUserOrBuilder extends
            // @@protoc_insertion_point(interface_extends:LoginUser)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>string username = 1;</code>
         */
        java.lang.String getUsername();

        /**
         * <code>string username = 1;</code>
         */
        com.google.protobuf.ByteString
        getUsernameBytes();

        /**
         * <code>string password = 2;</code>
         */
        java.lang.String getPassword();

        /**
         * <code>string password = 2;</code>
         */
        com.google.protobuf.ByteString
        getPasswordBytes();

        /**
         * <code>string code = 3;</code>
         */
        java.lang.String getCode();

        /**
         * <code>string code = 3;</code>
         */
        com.google.protobuf.ByteString
        getCodeBytes();

        /**
         * <code>string nickname = 4;</code>
         */
    java.lang.String getNickname();

        /**
         * <code>string nickname = 4;</code>
     */
    com.google.protobuf.ByteString
    getNicknameBytes();
    }

    /**
     * Protobuf type {@code LoginUser}
     */
    public static final class LoginUser extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:LoginUser)
            LoginUserOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use LoginUser.newBuilder() to construct.
        private LoginUser(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private LoginUser() {
            username_ = "";
            password_ = "";
            code_ = "";
            nickname_ = "";
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        private LoginUser(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            java.lang.String s = input.readStringRequireUtf8();

                            username_ = s;
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            password_ = s;
                            break;
                        }
                        case 26: {
                            java.lang.String s = input.readStringRequireUtf8();

                            code_ = s;
                            break;
                        }
                        case 34: {
                            java.lang.String s = input.readStringRequireUtf8();

                            nickname_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
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
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return UserMessage.internal_static_LoginUser_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return UserMessage.internal_static_LoginUser_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            UserMessage.LoginUser.class, UserMessage.LoginUser.Builder.class);
        }

        public static final int USERNAME_FIELD_NUMBER = 1;
        private volatile java.lang.Object username_;

        /**
         * <code>string username = 1;</code>
         */
        public java.lang.String getUsername() {
            java.lang.Object ref = username_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                username_ = s;
                return s;
            }
        }

        /**
         * <code>string username = 1;</code>
     */
    public com.google.protobuf.ByteString
    getUsernameBytes() {
        java.lang.Object ref = username_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8(
                            (java.lang.String) ref);
            username_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

        public static final int PASSWORD_FIELD_NUMBER = 2;
        private volatile java.lang.Object password_;

        /**
         * <code>string password = 2;</code>
         */
        public java.lang.String getPassword() {
            java.lang.Object ref = password_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                password_ = s;
                return s;
            }
        }

        /**
         * <code>string password = 2;</code>
     */
        public com.google.protobuf.ByteString
        getPasswordBytes() {
            java.lang.Object ref = password_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                password_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int CODE_FIELD_NUMBER = 3;
        private volatile java.lang.Object code_;

        /**
         * <code>string code = 3;</code>
         */
        public java.lang.String getCode() {
            java.lang.Object ref = code_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                code_ = s;
                return s;
            }
        }

        /**
         * <code>string code = 3;</code>
         */
        public com.google.protobuf.ByteString
        getCodeBytes() {
            java.lang.Object ref = code_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                code_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int NICKNAME_FIELD_NUMBER = 4;
        private volatile java.lang.Object nickname_;

        /**
         * <code>string nickname = 4;</code>
         */
        public java.lang.String getNickname() {
            java.lang.Object ref = nickname_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                nickname_ = s;
                return s;
            }
        }

        /**
         * <code>string nickname = 4;</code>
         */
        public com.google.protobuf.ByteString
        getNicknameBytes() {
            java.lang.Object ref = nickname_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                nickname_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        private byte memoizedIsInitialized = -1;

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (!getUsernameBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 1, username_);
            }
            if (!getPasswordBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, password_);
            }
            if (!getCodeBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 3, code_);
            }
            if (!getNicknameBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 4, nickname_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (!getUsernameBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, username_);
            }
            if (!getPasswordBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, password_);
            }
            if (!getCodeBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, code_);
            }
            if (!getNicknameBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, nickname_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UserMessage.LoginUser)) {
                return super.equals(obj);
            }
            UserMessage.LoginUser other = (UserMessage.LoginUser) obj;

            if (!getUsername()
                    .equals(other.getUsername())) return false;
            if (!getPassword()
                    .equals(other.getPassword())) return false;
            if (!getCode()
                    .equals(other.getCode())) return false;
            if (!getNickname()
                    .equals(other.getNickname())) return false;
            return unknownFields.equals(other.unknownFields);
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + USERNAME_FIELD_NUMBER;
            hash = (53 * hash) + getUsername().hashCode();
            hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
            hash = (53 * hash) + getPassword().hashCode();
            hash = (37 * hash) + CODE_FIELD_NUMBER;
            hash = (53 * hash) + getCode().hashCode();
            hash = (37 * hash) + NICKNAME_FIELD_NUMBER;
            hash = (53 * hash) + getNickname().hashCode();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static UserMessage.LoginUser parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UserMessage.LoginUser parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UserMessage.LoginUser parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UserMessage.LoginUser parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UserMessage.LoginUser parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UserMessage.LoginUser parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UserMessage.LoginUser parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static UserMessage.LoginUser parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static UserMessage.LoginUser parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static UserMessage.LoginUser parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static UserMessage.LoginUser parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static UserMessage.LoginUser parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UserMessage.LoginUser prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * Protobuf type {@code LoginUser}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:LoginUser)
                UserMessage.LoginUserOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return UserMessage.internal_static_LoginUser_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return UserMessage.internal_static_LoginUser_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                UserMessage.LoginUser.class, UserMessage.LoginUser.Builder.class);
            }

            // Construct using UserMessage.LoginUser.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                username_ = "";

                password_ = "";

                code_ = "";

                nickname_ = "";

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return UserMessage.internal_static_LoginUser_descriptor;
            }

            @java.lang.Override
            public UserMessage.LoginUser getDefaultInstanceForType() {
                return UserMessage.LoginUser.getDefaultInstance();
            }

            @java.lang.Override
            public UserMessage.LoginUser build() {
                UserMessage.LoginUser result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public UserMessage.LoginUser buildPartial() {
                UserMessage.LoginUser result = new UserMessage.LoginUser(this);
                result.username_ = username_;
                result.password_ = password_;
                result.code_ = code_;
                result.nickname_ = nickname_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof UserMessage.LoginUser) {
                    return mergeFrom((UserMessage.LoginUser) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(UserMessage.LoginUser other) {
                if (other == UserMessage.LoginUser.getDefaultInstance()) return this;
                if (!other.getUsername().isEmpty()) {
                    username_ = other.username_;
                    onChanged();
                }
                if (!other.getPassword().isEmpty()) {
                    password_ = other.password_;
                    onChanged();
                }
                if (!other.getCode().isEmpty()) {
                    code_ = other.code_;
                    onChanged();
                }
                if (!other.getNickname().isEmpty()) {
                    nickname_ = other.nickname_;
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                UserMessage.LoginUser parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (UserMessage.LoginUser) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private java.lang.Object username_ = "";

            /**
             * <code>string username = 1;</code>
             */
            public java.lang.String getUsername() {
                java.lang.Object ref = username_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    username_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string username = 1;</code>
             */
            public com.google.protobuf.ByteString
            getUsernameBytes() {
                java.lang.Object ref = username_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    username_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string username = 1;</code>
             */
            public Builder setUsername(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                username_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string username = 1;</code>
             */
            public Builder clearUsername() {

                username_ = getDefaultInstance().getUsername();
                onChanged();
                return this;
            }

            /**
             * <code>string username = 1;</code>
             */
            public Builder setUsernameBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                username_ = value;
                onChanged();
                return this;
            }

            private java.lang.Object password_ = "";

            /**
             * <code>string password = 2;</code>
             */
            public java.lang.String getPassword() {
                java.lang.Object ref = password_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    password_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string password = 2;</code>
             */
            public com.google.protobuf.ByteString
            getPasswordBytes() {
                java.lang.Object ref = password_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    password_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string password = 2;</code>
             */
            public Builder setPassword(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                password_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string password = 2;</code>
             */
            public Builder clearPassword() {

                password_ = getDefaultInstance().getPassword();
                onChanged();
                return this;
            }

            /**
             * <code>string password = 2;</code>
             */
            public Builder setPasswordBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                password_ = value;
                onChanged();
                return this;
            }

            private java.lang.Object code_ = "";

            /**
             * <code>string code = 3;</code>
             */
            public java.lang.String getCode() {
                java.lang.Object ref = code_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    code_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string code = 3;</code>
             */
            public com.google.protobuf.ByteString
            getCodeBytes() {
                java.lang.Object ref = code_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    code_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string code = 3;</code>
             */
            public Builder setCode(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                code_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string code = 3;</code>
             */
            public Builder clearCode() {

                code_ = getDefaultInstance().getCode();
                onChanged();
                return this;
            }

            /**
             * <code>string code = 3;</code>
             */
            public Builder setCodeBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                code_ = value;
                onChanged();
                return this;
            }

            private java.lang.Object nickname_ = "";

            /**
             * <code>string nickname = 4;</code>
             */
            public java.lang.String getNickname() {
                java.lang.Object ref = nickname_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    nickname_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string nickname = 4;</code>
             */
            public com.google.protobuf.ByteString
            getNicknameBytes() {
                java.lang.Object ref = nickname_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    nickname_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string nickname = 4;</code>
             */
            public Builder setNickname(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                nickname_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string nickname = 4;</code>
             */
            public Builder clearNickname() {

                nickname_ = getDefaultInstance().getNickname();
                onChanged();
                return this;
            }

            /**
             * <code>string nickname = 4;</code>
             */
            public Builder setNicknameBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                nickname_ = value;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:LoginUser)
        }

        // @@protoc_insertion_point(class_scope:LoginUser)
        private static final UserMessage.LoginUser DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new UserMessage.LoginUser();
        }

        public static UserMessage.LoginUser getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<LoginUser>
                PARSER = new com.google.protobuf.AbstractParser<LoginUser>() {
            @java.lang.Override
            public LoginUser parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new LoginUser(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<LoginUser> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<LoginUser> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public UserMessage.LoginUser getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface UserInfoOrBuilder extends
            // @@protoc_insertion_point(interface_extends:UserInfo)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int64 id = 1;</code>
         */
        long getId();

        /**
         * <code>string nickName = 2;</code>
         */
        java.lang.String getNickName();

        /**
         * <code>string nickName = 2;</code>
         */
        com.google.protobuf.ByteString
        getNickNameBytes();

        /**
         * <code>string headUrl = 3;</code>
     */
        java.lang.String getHeadUrl();

        /**
         * <code>string headUrl = 3;</code>
         */
        com.google.protobuf.ByteString
        getHeadUrlBytes();

        /**
         * <code>string email = 4;</code>
         */
        java.lang.String getEmail();

        /**
         * <code>string email = 4;</code>
         */
        com.google.protobuf.ByteString
        getEmailBytes();

        /**
         * <code>string major = 5;</code>
         */
        java.lang.String getMajor();

        /**
         * <code>string major = 5;</code>
         */
        com.google.protobuf.ByteString
        getMajorBytes();

        /**
         * <code>int32 age = 6;</code>
         */
        int getAge();

        /**
         * <code>int32 sex = 7;</code>
         */
        int getSex();
    }

    /**
     * Protobuf type {@code UserInfo}
     */
    public static final class UserInfo extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:UserInfo)
            UserInfoOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use UserInfo.newBuilder() to construct.
        private UserInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private UserInfo() {
            nickName_ = "";
            headUrl_ = "";
            email_ = "";
            major_ = "";
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        private UserInfo(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            id_ = input.readInt64();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            nickName_ = s;
                            break;
                        }
                        case 26: {
                            java.lang.String s = input.readStringRequireUtf8();

                            headUrl_ = s;
                            break;
                        }
                        case 34: {
                            java.lang.String s = input.readStringRequireUtf8();

                            email_ = s;
                            break;
                        }
                        case 42: {
                            java.lang.String s = input.readStringRequireUtf8();

                            major_ = s;
                            break;
                        }
                        case 48: {

                            age_ = input.readInt32();
                            break;
                        }
                        case 56: {

                            sex_ = input.readInt32();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
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
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return UserMessage.internal_static_UserInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return UserMessage.internal_static_UserInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            UserMessage.UserInfo.class, UserMessage.UserInfo.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private long id_;

        /**
         * <code>int64 id = 1;</code>
         */
        public long getId() {
            return id_;
        }

        public static final int NICKNAME_FIELD_NUMBER = 2;
        private volatile java.lang.Object nickName_;

        /**
         * <code>string nickName = 2;</code>
         */
        public java.lang.String getNickName() {
            java.lang.Object ref = nickName_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                nickName_ = s;
                return s;
            }
        }

        /**
         * <code>string nickName = 2;</code>
         */
        public com.google.protobuf.ByteString
        getNickNameBytes() {
            java.lang.Object ref = nickName_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                nickName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int HEADURL_FIELD_NUMBER = 3;
        private volatile java.lang.Object headUrl_;

        /**
         * <code>string headUrl = 3;</code>
         */
        public java.lang.String getHeadUrl() {
            java.lang.Object ref = headUrl_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                headUrl_ = s;
                return s;
            }
        }

        /**
         * <code>string headUrl = 3;</code>
         */
        public com.google.protobuf.ByteString
        getHeadUrlBytes() {
            java.lang.Object ref = headUrl_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                headUrl_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int EMAIL_FIELD_NUMBER = 4;
        private volatile java.lang.Object email_;

        /**
         * <code>string email = 4;</code>
         */
        public java.lang.String getEmail() {
            java.lang.Object ref = email_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                email_ = s;
                return s;
            }
        }

        /**
         * <code>string email = 4;</code>
         */
        public com.google.protobuf.ByteString
        getEmailBytes() {
            java.lang.Object ref = email_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                email_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int MAJOR_FIELD_NUMBER = 5;
        private volatile java.lang.Object major_;

        /**
         * <code>string major = 5;</code>
         */
        public java.lang.String getMajor() {
            java.lang.Object ref = major_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                major_ = s;
                return s;
            }
        }

        /**
         * <code>string major = 5;</code>
         */
        public com.google.protobuf.ByteString
        getMajorBytes() {
            java.lang.Object ref = major_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                major_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int AGE_FIELD_NUMBER = 6;
        private int age_;

        /**
         * <code>int32 age = 6;</code>
         */
        public int getAge() {
            return age_;
        }

        public static final int SEX_FIELD_NUMBER = 7;
        private int sex_;

        /**
         * <code>int32 sex = 7;</code>
         */
        public int getSex() {
            return sex_;
        }

        private byte memoizedIsInitialized = -1;

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (id_ != 0L) {
                output.writeInt64(1, id_);
            }
            if (!getNickNameBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, nickName_);
            }
            if (!getHeadUrlBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 3, headUrl_);
            }
            if (!getEmailBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 4, email_);
            }
            if (!getMajorBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 5, major_);
            }
            if (age_ != 0) {
                output.writeInt32(6, age_);
            }
            if (sex_ != 0) {
                output.writeInt32(7, sex_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (id_ != 0L) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt64Size(1, id_);
            }
            if (!getNickNameBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, nickName_);
            }
            if (!getHeadUrlBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, headUrl_);
            }
            if (!getEmailBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, email_);
            }
            if (!getMajorBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, major_);
            }
            if (age_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(6, age_);
            }
            if (sex_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(7, sex_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UserMessage.UserInfo)) {
                return super.equals(obj);
            }
            UserMessage.UserInfo other = (UserMessage.UserInfo) obj;

            if (getId()
                    != other.getId()) return false;
            if (!getNickName()
                    .equals(other.getNickName())) return false;
            if (!getHeadUrl()
                    .equals(other.getHeadUrl())) return false;
            if (!getEmail()
                    .equals(other.getEmail())) return false;
            if (!getMajor()
                    .equals(other.getMajor())) return false;
            if (getAge()
                    != other.getAge()) return false;
            if (getSex()
                    != other.getSex()) return false;
            return unknownFields.equals(other.unknownFields);
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
                    getId());
            hash = (37 * hash) + NICKNAME_FIELD_NUMBER;
            hash = (53 * hash) + getNickName().hashCode();
            hash = (37 * hash) + HEADURL_FIELD_NUMBER;
            hash = (53 * hash) + getHeadUrl().hashCode();
            hash = (37 * hash) + EMAIL_FIELD_NUMBER;
            hash = (53 * hash) + getEmail().hashCode();
            hash = (37 * hash) + MAJOR_FIELD_NUMBER;
            hash = (53 * hash) + getMajor().hashCode();
            hash = (37 * hash) + AGE_FIELD_NUMBER;
            hash = (53 * hash) + getAge();
            hash = (37 * hash) + SEX_FIELD_NUMBER;
            hash = (53 * hash) + getSex();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static UserMessage.UserInfo parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UserMessage.UserInfo parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UserMessage.UserInfo parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UserMessage.UserInfo parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UserMessage.UserInfo parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UserMessage.UserInfo parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UserMessage.UserInfo parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static UserMessage.UserInfo parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static UserMessage.UserInfo parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static UserMessage.UserInfo parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static UserMessage.UserInfo parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static UserMessage.UserInfo parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UserMessage.UserInfo prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * Protobuf type {@code UserInfo}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:UserInfo)
                UserMessage.UserInfoOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return UserMessage.internal_static_UserInfo_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return UserMessage.internal_static_UserInfo_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                UserMessage.UserInfo.class, UserMessage.UserInfo.Builder.class);
            }

            // Construct using UserMessage.UserInfo.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0L;

                nickName_ = "";

                headUrl_ = "";

                email_ = "";

                major_ = "";

                age_ = 0;

                sex_ = 0;

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return UserMessage.internal_static_UserInfo_descriptor;
            }

            @java.lang.Override
            public UserMessage.UserInfo getDefaultInstanceForType() {
                return UserMessage.UserInfo.getDefaultInstance();
            }

            @java.lang.Override
            public UserMessage.UserInfo build() {
                UserMessage.UserInfo result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public UserMessage.UserInfo buildPartial() {
                UserMessage.UserInfo result = new UserMessage.UserInfo(this);
                result.id_ = id_;
                result.nickName_ = nickName_;
                result.headUrl_ = headUrl_;
                result.email_ = email_;
                result.major_ = major_;
                result.age_ = age_;
                result.sex_ = sex_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof UserMessage.UserInfo) {
                    return mergeFrom((UserMessage.UserInfo) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(UserMessage.UserInfo other) {
                if (other == UserMessage.UserInfo.getDefaultInstance()) return this;
                if (other.getId() != 0L) {
                    setId(other.getId());
                }
                if (!other.getNickName().isEmpty()) {
                    nickName_ = other.nickName_;
                    onChanged();
                }
                if (!other.getHeadUrl().isEmpty()) {
                    headUrl_ = other.headUrl_;
                    onChanged();
                }
                if (!other.getEmail().isEmpty()) {
                    email_ = other.email_;
                    onChanged();
                }
                if (!other.getMajor().isEmpty()) {
                    major_ = other.major_;
                    onChanged();
                }
                if (other.getAge() != 0) {
                    setAge(other.getAge());
                }
                if (other.getSex() != 0) {
                    setSex(other.getSex());
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                UserMessage.UserInfo parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (UserMessage.UserInfo) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private long id_;

            /**
             * <code>int64 id = 1;</code>
             */
            public long getId() {
                return id_;
            }

            /**
             * <code>int64 id = 1;</code>
             */
            public Builder setId(long value) {

                id_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int64 id = 1;</code>
             */
            public Builder clearId() {

                id_ = 0L;
                onChanged();
                return this;
            }

            private java.lang.Object nickName_ = "";

            /**
             * <code>string nickName = 2;</code>
             */
            public java.lang.String getNickName() {
                java.lang.Object ref = nickName_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    nickName_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string nickName = 2;</code>
             */
            public com.google.protobuf.ByteString
            getNickNameBytes() {
                java.lang.Object ref = nickName_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    nickName_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string nickName = 2;</code>
             */
            public Builder setNickName(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                nickName_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string nickName = 2;</code>
             */
            public Builder clearNickName() {

                nickName_ = getDefaultInstance().getNickName();
                onChanged();
                return this;
            }

            /**
             * <code>string nickName = 2;</code>
             */
            public Builder setNickNameBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                nickName_ = value;
                onChanged();
                return this;
            }

            private java.lang.Object headUrl_ = "";

            /**
             * <code>string headUrl = 3;</code>
             */
            public java.lang.String getHeadUrl() {
                java.lang.Object ref = headUrl_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    headUrl_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string headUrl = 3;</code>
             */
            public com.google.protobuf.ByteString
            getHeadUrlBytes() {
                java.lang.Object ref = headUrl_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    headUrl_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string headUrl = 3;</code>
             */
            public Builder setHeadUrl(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                headUrl_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string headUrl = 3;</code>
             */
            public Builder clearHeadUrl() {

                headUrl_ = getDefaultInstance().getHeadUrl();
                onChanged();
                return this;
            }

            /**
             * <code>string headUrl = 3;</code>
             */
            public Builder setHeadUrlBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                headUrl_ = value;
                onChanged();
                return this;
            }

            private java.lang.Object email_ = "";

            /**
             * <code>string email = 4;</code>
             */
            public java.lang.String getEmail() {
                java.lang.Object ref = email_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    email_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string email = 4;</code>
             */
            public com.google.protobuf.ByteString
            getEmailBytes() {
                java.lang.Object ref = email_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    email_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string email = 4;</code>
             */
            public Builder setEmail(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                email_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string email = 4;</code>
             */
            public Builder clearEmail() {

                email_ = getDefaultInstance().getEmail();
                onChanged();
                return this;
            }

            /**
             * <code>string email = 4;</code>
             */
            public Builder setEmailBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                email_ = value;
                onChanged();
                return this;
            }

            private java.lang.Object major_ = "";

            /**
             * <code>string major = 5;</code>
             */
            public java.lang.String getMajor() {
                java.lang.Object ref = major_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    major_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string major = 5;</code>
             */
            public com.google.protobuf.ByteString
            getMajorBytes() {
                java.lang.Object ref = major_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    major_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string major = 5;</code>
             */
            public Builder setMajor(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                major_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string major = 5;</code>
             */
            public Builder clearMajor() {

                major_ = getDefaultInstance().getMajor();
                onChanged();
                return this;
            }

            /**
             * <code>string major = 5;</code>
             */
            public Builder setMajorBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                major_ = value;
                onChanged();
                return this;
            }

            private int age_;

            /**
             * <code>int32 age = 6;</code>
             */
            public int getAge() {
                return age_;
            }

            /**
             * <code>int32 age = 6;</code>
             */
            public Builder setAge(int value) {

                age_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int32 age = 6;</code>
             */
            public Builder clearAge() {

                age_ = 0;
                onChanged();
                return this;
            }

            private int sex_;

            /**
             * <code>int32 sex = 7;</code>
             */
            public int getSex() {
                return sex_;
            }

            /**
             * <code>int32 sex = 7;</code>
             */
            public Builder setSex(int value) {

                sex_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int32 sex = 7;</code>
             */
            public Builder clearSex() {

                sex_ = 0;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:UserInfo)
        }

        // @@protoc_insertion_point(class_scope:UserInfo)
        private static final UserMessage.UserInfo DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new UserMessage.UserInfo();
        }

        public static UserMessage.UserInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<UserInfo>
                PARSER = new com.google.protobuf.AbstractParser<UserInfo>() {
            @java.lang.Override
            public UserInfo parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new UserInfo(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<UserInfo> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<UserInfo> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public UserMessage.UserInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface IntRetOrBuilder extends
            // @@protoc_insertion_point(interface_extends:IntRet)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int32 ret = 1;</code>
         */
        int getRet();
    }

    /**
     * Protobuf type {@code IntRet}
     */
    public static final class IntRet extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:IntRet)
            IntRetOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use IntRet.newBuilder() to construct.
        private IntRet(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private IntRet() {
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        private IntRet(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            ret_ = input.readInt32();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
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
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return UserMessage.internal_static_IntRet_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return UserMessage.internal_static_IntRet_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            UserMessage.IntRet.class, UserMessage.IntRet.Builder.class);
        }

        public static final int RET_FIELD_NUMBER = 1;
        private int ret_;

        /**
         * <code>int32 ret = 1;</code>
         */
        public int getRet() {
            return ret_;
        }

        private byte memoizedIsInitialized = -1;

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (ret_ != 0) {
                output.writeInt32(1, ret_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (ret_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, ret_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UserMessage.IntRet)) {
                return super.equals(obj);
            }
            UserMessage.IntRet other = (UserMessage.IntRet) obj;

            if (getRet()
                    != other.getRet()) return false;
            return unknownFields.equals(other.unknownFields);
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + RET_FIELD_NUMBER;
            hash = (53 * hash) + getRet();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static UserMessage.IntRet parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UserMessage.IntRet parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UserMessage.IntRet parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UserMessage.IntRet parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UserMessage.IntRet parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UserMessage.IntRet parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UserMessage.IntRet parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static UserMessage.IntRet parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static UserMessage.IntRet parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static UserMessage.IntRet parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static UserMessage.IntRet parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static UserMessage.IntRet parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UserMessage.IntRet prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * Protobuf type {@code IntRet}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:IntRet)
                UserMessage.IntRetOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return UserMessage.internal_static_IntRet_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return UserMessage.internal_static_IntRet_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                UserMessage.IntRet.class, UserMessage.IntRet.Builder.class);
            }

            // Construct using UserMessage.IntRet.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                ret_ = 0;

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return UserMessage.internal_static_IntRet_descriptor;
            }

            @java.lang.Override
            public UserMessage.IntRet getDefaultInstanceForType() {
                return UserMessage.IntRet.getDefaultInstance();
            }

            @java.lang.Override
            public UserMessage.IntRet build() {
                UserMessage.IntRet result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public UserMessage.IntRet buildPartial() {
                UserMessage.IntRet result = new UserMessage.IntRet(this);
                result.ret_ = ret_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof UserMessage.IntRet) {
                    return mergeFrom((UserMessage.IntRet) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(UserMessage.IntRet other) {
                if (other == UserMessage.IntRet.getDefaultInstance()) return this;
                if (other.getRet() != 0) {
                    setRet(other.getRet());
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                UserMessage.IntRet parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (UserMessage.IntRet) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int ret_;

            /**
             * <code>int32 ret = 1;</code>
             */
            public int getRet() {
                return ret_;
            }

            /**
             * <code>int32 ret = 1;</code>
             */
            public Builder setRet(int value) {

                ret_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int32 ret = 1;</code>
             */
            public Builder clearRet() {

                ret_ = 0;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:IntRet)
        }

        // @@protoc_insertion_point(class_scope:IntRet)
        private static final UserMessage.IntRet DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new UserMessage.IntRet();
        }

        public static UserMessage.IntRet getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<IntRet>
                PARSER = new com.google.protobuf.AbstractParser<IntRet>() {
            @java.lang.Override
            public IntRet parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new IntRet(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<IntRet> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<IntRet> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public UserMessage.IntRet getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface StringRetOrBuilder extends
            // @@protoc_insertion_point(interface_extends:StringRet)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>string ret = 1;</code>
         */
        java.lang.String getRet();

        /**
         * <code>string ret = 1;</code>
         */
        com.google.protobuf.ByteString
        getRetBytes();
  }
  /**
   * Protobuf type {@code StringRet}
   */
  public static final class StringRet extends
          com.google.protobuf.GeneratedMessageV3 implements
          // @@protoc_insertion_point(message_implements:StringRet)
          StringRetOrBuilder {
      private static final long serialVersionUID = 0L;

      // Use StringRet.newBuilder() to construct.
      private StringRet(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
          super(builder);
      }

      private StringRet() {
          ret_ = "";
      }

      @java.lang.Override
      public final com.google.protobuf.UnknownFieldSet
      getUnknownFields() {
          return this.unknownFields;
      }

      private StringRet(
              com.google.protobuf.CodedInputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws com.google.protobuf.InvalidProtocolBufferException {
          this();
          if (extensionRegistry == null) {
              throw new java.lang.NullPointerException();
          }
          int mutable_bitField0_ = 0;
          com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                  com.google.protobuf.UnknownFieldSet.newBuilder();
          try {
              boolean done = false;
              while (!done) {
                  int tag = input.readTag();
          switch (tag) {
              case 0:
                  done = true;
                  break;
              case 10: {
                  java.lang.String s = input.readStringRequireUtf8();

              ret_ = s;
                  break;
              }
              default: {
                  if (!parseUnknownField(
                          input, unknownFields, extensionRegistry, tag)) {
                      done = true;
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
              this.unknownFields = unknownFields.build();
              makeExtensionsImmutable();
          }
      }

      public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
          return UserMessage.internal_static_StringRet_descriptor;
      }

      @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
          return UserMessage.internal_static_StringRet_fieldAccessorTable
                  .ensureFieldAccessorsInitialized(
                          UserMessage.StringRet.class, UserMessage.StringRet.Builder.class);
      }

      public static final int RET_FIELD_NUMBER = 1;
      private volatile java.lang.Object ret_;

      /**
       * <code>string ret = 1;</code>
       */
      public java.lang.String getRet() {
          java.lang.Object ref = ret_;
          if (ref instanceof java.lang.String) {
              return (java.lang.String) ref;
          } else {
              com.google.protobuf.ByteString bs =
                      (com.google.protobuf.ByteString) ref;
              java.lang.String s = bs.toStringUtf8();
              ret_ = s;
              return s;
          }
      }

      /**
       * <code>string ret = 1;</code>
       */
      public com.google.protobuf.ByteString
      getRetBytes() {
          java.lang.Object ref = ret_;
          if (ref instanceof java.lang.String) {
              com.google.protobuf.ByteString b =
                      com.google.protobuf.ByteString.copyFromUtf8(
                              (java.lang.String) ref);
              ret_ = b;
              return b;
          } else {
              return (com.google.protobuf.ByteString) ref;
          }
      }

      private byte memoizedIsInitialized = -1;

      @java.lang.Override
      public final boolean isInitialized() {
          byte isInitialized = memoizedIsInitialized;
          if (isInitialized == 1) return true;
          if (isInitialized == 0) return false;

          memoizedIsInitialized = 1;
          return true;
      }

      @java.lang.Override
      public void writeTo(com.google.protobuf.CodedOutputStream output)
              throws java.io.IOException {
          if (!getRetBytes().isEmpty()) {
              com.google.protobuf.GeneratedMessageV3.writeString(output, 1, ret_);
          }
          unknownFields.writeTo(output);
      }

      @java.lang.Override
      public int getSerializedSize() {
          int size = memoizedSize;
          if (size != -1) return size;

          size = 0;
          if (!getRetBytes().isEmpty()) {
              size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, ret_);
          }
          size += unknownFields.getSerializedSize();
          memoizedSize = size;
          return size;
      }

      @java.lang.Override
      public boolean equals(final java.lang.Object obj) {
          if (obj == this) {
              return true;
          }
          if (!(obj instanceof UserMessage.StringRet)) {
              return super.equals(obj);
          }
          UserMessage.StringRet other = (UserMessage.StringRet) obj;

          if (!getRet()
                  .equals(other.getRet())) return false;
          return unknownFields.equals(other.unknownFields);
      }

      @java.lang.Override
      public int hashCode() {
          if (memoizedHashCode != 0) {
              return memoizedHashCode;
          }
          int hash = 41;
          hash = (19 * hash) + getDescriptor().hashCode();
          hash = (37 * hash) + RET_FIELD_NUMBER;
          hash = (53 * hash) + getRet().hashCode();
          hash = (29 * hash) + unknownFields.hashCode();
          memoizedHashCode = hash;
          return hash;
      }

      public static UserMessage.StringRet parseFrom(
              java.nio.ByteBuffer data)
              throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data);
      }

      public static UserMessage.StringRet parseFrom(
              java.nio.ByteBuffer data,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data, extensionRegistry);
      }

      public static UserMessage.StringRet parseFrom(
              com.google.protobuf.ByteString data)
              throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data);
      }

      public static UserMessage.StringRet parseFrom(
              com.google.protobuf.ByteString data,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data, extensionRegistry);
      }

      public static UserMessage.StringRet parseFrom(byte[] data)
              throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data);
      }

      public static UserMessage.StringRet parseFrom(
              byte[] data,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data, extensionRegistry);
      }

      public static UserMessage.StringRet parseFrom(java.io.InputStream input)
              throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
                  .parseWithIOException(PARSER, input);
      }

      public static UserMessage.StringRet parseFrom(
              java.io.InputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
                  .parseWithIOException(PARSER, input, extensionRegistry);
      }

      public static UserMessage.StringRet parseDelimitedFrom(java.io.InputStream input)
              throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
                  .parseDelimitedWithIOException(PARSER, input);
      }

      public static UserMessage.StringRet parseDelimitedFrom(
              java.io.InputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
                  .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }

      public static UserMessage.StringRet parseFrom(
              com.google.protobuf.CodedInputStream input)
              throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
                  .parseWithIOException(PARSER, input);
      }

      public static UserMessage.StringRet parseFrom(
              com.google.protobuf.CodedInputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
                  .parseWithIOException(PARSER, input, extensionRegistry);
      }

      @java.lang.Override
      public Builder newBuilderForType() {
          return newBuilder();
      }

      public static Builder newBuilder() {
          return DEFAULT_INSTANCE.toBuilder();
      }

      public static Builder newBuilder(UserMessage.StringRet prototype) {
          return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }

      @java.lang.Override
      public Builder toBuilder() {
          return this == DEFAULT_INSTANCE
                  ? new Builder() : new Builder().mergeFrom(this);
      }

      @java.lang.Override
      protected Builder newBuilderForType(
              com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
          Builder builder = new Builder(parent);
          return builder;
      }

      /**
       * Protobuf type {@code StringRet}
       */
      public static final class Builder extends
              com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
              // @@protoc_insertion_point(builder_implements:StringRet)
              UserMessage.StringRetOrBuilder {
          public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
              return UserMessage.internal_static_StringRet_descriptor;
          }

          @java.lang.Override
          protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
              return UserMessage.internal_static_StringRet_fieldAccessorTable
                      .ensureFieldAccessorsInitialized(
                              UserMessage.StringRet.class, UserMessage.StringRet.Builder.class);
          }

          // Construct using UserMessage.StringRet.newBuilder()
          private Builder() {
              maybeForceBuilderInitialization();
          }

          private Builder(
                  com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
              super(parent);
              maybeForceBuilderInitialization();
          }

          private void maybeForceBuilderInitialization() {
              if (com.google.protobuf.GeneratedMessageV3
                      .alwaysUseFieldBuilders) {
              }
          }

          @java.lang.Override
          public Builder clear() {
              super.clear();
              ret_ = "";

              return this;
          }

          @java.lang.Override
          public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
              return UserMessage.internal_static_StringRet_descriptor;
          }

          @java.lang.Override
          public UserMessage.StringRet getDefaultInstanceForType() {
              return UserMessage.StringRet.getDefaultInstance();
          }

          @java.lang.Override
          public UserMessage.StringRet build() {
              UserMessage.StringRet result = buildPartial();
              if (!result.isInitialized()) {
                  throw newUninitializedMessageException(result);
              }
              return result;
          }

          @java.lang.Override
          public UserMessage.StringRet buildPartial() {
              UserMessage.StringRet result = new UserMessage.StringRet(this);
              result.ret_ = ret_;
              onBuilt();
              return result;
          }

          @java.lang.Override
          public Builder clone() {
              return super.clone();
          }

          @java.lang.Override
          public Builder setField(
                  com.google.protobuf.Descriptors.FieldDescriptor field,
                  java.lang.Object value) {
              return super.setField(field, value);
          }

          @java.lang.Override
          public Builder clearField(
                  com.google.protobuf.Descriptors.FieldDescriptor field) {
              return super.clearField(field);
          }

          @java.lang.Override
          public Builder clearOneof(
                  com.google.protobuf.Descriptors.OneofDescriptor oneof) {
              return super.clearOneof(oneof);
          }

          @java.lang.Override
          public Builder setRepeatedField(
                  com.google.protobuf.Descriptors.FieldDescriptor field,
                  int index, java.lang.Object value) {
              return super.setRepeatedField(field, index, value);
          }

          @java.lang.Override
          public Builder addRepeatedField(
                  com.google.protobuf.Descriptors.FieldDescriptor field,
                  java.lang.Object value) {
              return super.addRepeatedField(field, value);
          }

          @java.lang.Override
          public Builder mergeFrom(com.google.protobuf.Message other) {
              if (other instanceof UserMessage.StringRet) {
                  return mergeFrom((UserMessage.StringRet) other);
              } else {
                  super.mergeFrom(other);
                  return this;
              }
          }

          public Builder mergeFrom(UserMessage.StringRet other) {
              if (other == UserMessage.StringRet.getDefaultInstance()) return this;
              if (!other.getRet().isEmpty()) {
                  ret_ = other.ret_;
                  onChanged();
              }
              this.mergeUnknownFields(other.unknownFields);
              onChanged();
              return this;
          }

          @java.lang.Override
          public final boolean isInitialized() {
              return true;
          }

          @java.lang.Override
          public Builder mergeFrom(
                  com.google.protobuf.CodedInputStream input,
                  com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                  throws java.io.IOException {
              UserMessage.StringRet parsedMessage = null;
              try {
                  parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
              } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                  parsedMessage = (UserMessage.StringRet) e.getUnfinishedMessage();
                  throw e.unwrapIOException();
              } finally {
                  if (parsedMessage != null) {
                      mergeFrom(parsedMessage);
          }
        }
        return this;
          }

          private java.lang.Object ret_ = "";

          /**
           * <code>string ret = 1;</code>
           */
          public java.lang.String getRet() {
              java.lang.Object ref = ret_;
              if (!(ref instanceof java.lang.String)) {
                  com.google.protobuf.ByteString bs =
                          (com.google.protobuf.ByteString) ref;
                  java.lang.String s = bs.toStringUtf8();
                  ret_ = s;
                  return s;
              } else {
                  return (java.lang.String) ref;
              }
          }

          /**
           * <code>string ret = 1;</code>
           */
          public com.google.protobuf.ByteString
          getRetBytes() {
        java.lang.Object ref = ret_;
        if (ref instanceof String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8(
                            (java.lang.String) ref);
            ret_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
          }
      /**
       * <code>string ret = 1;</code>
       */
      public Builder setRet(
              java.lang.String value) {
          if (value == null) {
              throw new NullPointerException();
          }

          ret_ = value;
          onChanged();
          return this;
      }

          /**
       * <code>string ret = 1;</code>
       */
      public Builder clearRet() {
        
        ret_ = getDefaultInstance().getRet();
          onChanged();
          return this;
      }

          /**
           * <code>string ret = 1;</code>
           */
          public Builder setRetBytes(
                  com.google.protobuf.ByteString value) {
              if (value == null) {
                  throw new NullPointerException();
              }
              checkByteStringIsUtf8(value);

              ret_ = value;
        onChanged();
              return this;
          }

          @java.lang.Override
          public final Builder setUnknownFields(
                  final com.google.protobuf.UnknownFieldSet unknownFields) {
              return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
              final com.google.protobuf.UnknownFieldSet unknownFields) {
          return super.mergeUnknownFields(unknownFields);
      }


          // @@protoc_insertion_point(builder_scope:StringRet)
      }

      // @@protoc_insertion_point(class_scope:StringRet)
      private static final UserMessage.StringRet DEFAULT_INSTANCE;

      static {
          DEFAULT_INSTANCE = new UserMessage.StringRet();
      }

      public static UserMessage.StringRet getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<StringRet>
            PARSER = new com.google.protobuf.AbstractParser<StringRet>() {
        @java.lang.Override
        public StringRet parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return new StringRet(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<StringRet> parser() {
        return PARSER;
    }

      @java.lang.Override
      public com.google.protobuf.Parser<StringRet> getParserForType() {
          return PARSER;
    }

    @java.lang.Override
    public UserMessage.StringRet getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

  }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_LoginUser_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_LoginUser_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_UserInfo_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_UserInfo_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_IntRet_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_IntRet_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_StringRet_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_StringRet_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        java.lang.String[] descriptorData = {
                "\n\021UserMessage.proto\"O\n\tLoginUser\022\020\n\010user" +
                        "name\030\001 \001(\t\022\020\n\010password\030\002 \001(\t\022\014\n\004code\030\003 \001" +
      "(\t\022\020\n\010nickname\030\004 \001(\t\"q\n\010UserInfo\022\n\n\002id\030\001" +
      " \001(\003\022\020\n\010nickName\030\002 \001(\t\022\017\n\007headUrl\030\003 \001(\t\022" +
      "\r\n\005email\030\004 \001(\t\022\r\n\005major\030\005 \001(\t\022\013\n\003age\030\006 \001" +
      "(\005\022\013\n\003sex\030\007 \001(\005\"\025\n\006IntRet\022\013\n\003ret\030\001 \001(\005\"\030" +
      "\n\tStringRet\022\013\n\003ret\030\001 \001(\t2\246\002\n\013UserService" +
      "\022\037\n\005login\022\n.LoginUser\032\n.StringRet\022\035\n\006log" +
      "out\022\n.StringRet\032\007.IntRet\022\037\n\010register\022\n.L" +
      "oginUser\032\007.IntRet\022$\n\rresetPassword\022\n.Log" +
      "inUser\032\007.IntRet\022!\n\nloginCheck\022\n.StringRe" +
      "t\032\007.IntRet\022%\n\016checkUserExist\022\n.StringRet" +
      "\032\007.IntRet\022$\n\013getUserInfo\022\n.StringRet\032\t.U" +
      "serInfo\022 \n\nupdateUser\022\t.UserInfo\032\007.IntRe" +
      "tB\rB\013UserMessageb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
            public com.google.protobuf.ExtensionRegistry assignDescriptors(
                    com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_LoginUser_descriptor =
            getDescriptor().getMessageTypes().get(0);
    internal_static_LoginUser_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LoginUser_descriptor,
        new java.lang.String[] { "Username", "Password", "Code", "Nickname", });
    internal_static_UserInfo_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_UserInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserInfo_descriptor,
        new java.lang.String[] { "Id", "NickName", "HeadUrl", "Email", "Major", "Age", "Sex", });
    internal_static_IntRet_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_IntRet_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_IntRet_descriptor,
        new java.lang.String[] { "Ret", });
    internal_static_StringRet_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_StringRet_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StringRet_descriptor,
        new java.lang.String[] { "Ret", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

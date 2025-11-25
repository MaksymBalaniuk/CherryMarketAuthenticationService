package com.cherrymarket.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public class CredentialsHidingPolicy<D> {

    private final List<CredentialsHidingPolicyBuilder.CredentialField<D, ?>> credentialFields;

    private CredentialsHidingPolicy(List<CredentialsHidingPolicyBuilder.CredentialField<D, ?>> credentialFields) {
        this.credentialFields = credentialFields;
    }

    public static <D> CredentialsHidingPolicyBuilder<D> builder() {
        return new CredentialsHidingPolicyBuilder<>();
    }

    public D apply(D data) {
        credentialFields.forEach(f -> f.accept(data));
        return data;
    }

    public static class CredentialsHidingPolicyBuilder<D> {

        private final List<CredentialField<D, ?>> credentialFields = new ArrayList<>();

        private CredentialsHidingPolicyBuilder() {}

        public <V> CredentialsHidingPolicyBuilder<D> field(BiConsumer<D, V> setter, boolean hide, V replacementValue) {
            credentialFields.add(new CredentialField<>(setter, hide, replacementValue));
            return this;
        }

        public CredentialsHidingPolicy<D> build() {
            return new CredentialsHidingPolicy<>(credentialFields);
        }

        private record CredentialField<D, V>(BiConsumer<D, V> setter, boolean hide, V replacementValue) {

            private void accept(D data) {
                if (Objects.nonNull(data) && Objects.nonNull(setter) && hide)
                    setter.accept(data, replacementValue);
            }

        }

    }

}

import org.jboss.logging.Logger;
import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.oidc.mappers.AbstractOIDCProtocolMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenMapper;
import org.keycloak.protocol.oidc.mappers.OIDCIDTokenMapper;
import org.keycloak.protocol.oidc.mappers.UserInfoTokenMapper;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.AccessToken;

import java.util.ArrayList;
import java.util.List;

public class CustomOIDCProtocolMapper extends AbstractOIDCProtocolMapper implements OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper {
    public static final String PROVIDER_ID = "oidc-customprotocolmapper";

    private static final Logger LOGGER = Logger.getLogger(CustomOIDCProtocolMapper.class.getName());
    private static final List<ProviderConfigProperty> configProperties = new ArrayList<ProviderConfigProperty>();

    static {
        ProviderConfigProperty property = new ProviderConfigProperty();
        property.setName("url.external.api.value");
        property.setLabel("URL External API");
        property.setType("String");
        property.setHelpText("url to the external service");
        configProperties.add(property);
    }
    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

    @Override
    public String getDisplayCategory() {
        return TOKEN_MAPPER_CATEGORY;
    }

    @Override
    public String getDisplayType() {
        return "Custom Ozan Claim Mapper";
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getHelpText() {
        return "baba buralarda gezme baba";
    }

    public AccessToken transformAccessToken(AccessToken token, ProtocolMapperModel mappingModel, KeycloakSession keycloakSession,
                                            UserSessionModel userSession, ClientSessionContext clientSessionCtx) {
        String userId = userSession.getUser().getId();

        final String externalUrl = mappingModel.getConfig().get("url.external.api.value").concat(userId);

        LOGGER.info("External URL: " + externalUrl);

        token.getOtherClaims().put("custom_permissions", PermissionService.findAllUserPermissions(externalUrl));

        setClaim(token, mappingModel, userSession, keycloakSession, clientSessionCtx);

        return token;
    }

}
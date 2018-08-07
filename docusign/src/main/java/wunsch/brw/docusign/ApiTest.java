package wunsch.brw.docusign;

import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.Configuration;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class ApiTest {

   public static void main(String[] args) {


      // Java setup and config
      String IntegratorKey = "e7f8aa0b-51d4-49c2-a8fc-3d3d474ec985";

      // generate a client secret for the integrator key you supply above, again through sandbox admin menu
      String ClientSecret = "4c928a4c-84bc-450f-9961-7ab74bca3916";

      // must match a redirect URI (case-sensitive) you configured on the key
      String RedirectURI = "https://correlloilgas.com/";

      // use demo authentication server (remove -d for production)
      String AuthServerUrl = "https://account-d.docusign.com";

      // point to the demo (sandbox) environment. For production requests your account sub-domain
      // will vary, you should always use the base URI that is returned from authentication to
      // ensure your integration points to the correct endpoints (in both environments)
      String RestApiUrl = "https://demo.docusign.net/restapi";

      // instantiate the api client and point to auth server
      ApiClient apiClient = new ApiClient(AuthServerUrl, "docusignAccessCode", IntegratorKey, ClientSecret);

      // set the base path for REST API requests
      apiClient.setBasePath(RestApiUrl);

      // configure the authorization flow on the api client
      apiClient.configureAuthorizationFlow(IntegratorKey,ClientSecret,RedirectURI);

      // set as default api client in your configuration
      Configuration.setDefaultApiClient(apiClient);

      try

      {
      // get DocuSign OAuth authorization url
         String oauthLoginUrl = apiClient.getAuthorizationUri();
      // open DocuSign OAuth login in the browser
         System.out.println(oauthLoginUrl);
         Desktop.getDesktop().browse(URI.create(oauthLoginUrl));
      }
      catch(OAuthSystemException ex)
      {
         System.out.println(ex);
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}

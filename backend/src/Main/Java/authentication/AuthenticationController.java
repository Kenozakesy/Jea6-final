package authentication;

import domain.UserLogin;
import domain.accoun.Account;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import domain.accoun.Role;
import repository.AccountRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;

@Path("/authentication")
public class AuthenticationController {

    @EJB
    private AccountRepository accountRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(UserLogin login)
    {
        try {
            // Authenticate the user using the credentials provided

            Account account = authenticate(login.getUsername(), login.getPassword());

            // Issue a token for the user
            String token = issueToken(account, new String[4]);

            System.out.println(token);
            // Return the token on the response
            System.out.println("sending token: " + token);
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    //check if user even exists
    private Account authenticate(String username, String password) throws Exception {
        Account account = accountRepository.getUser(username, password);
        if(account != null){
            return account;
        }
        else {
            throw new Exception();
        }
    }

    // creates a new token with the name of the user
    private String issueToken(Account account, String[] roles) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        Date date = calendar.getTime();

        try {

            Algorithm algorithm = Algorithm.HMAC256("SecretKey");
            String token = JWT.create()
                    .withIssuer("test")
                    .withClaim("User",account.getName())
                    .withClaim("ID", account.getId())
                    .withClaim("Roles" , String.valueOf(Role.USER))
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .sign(algorithm);
            return token;
        }catch (Exception e){
            return "";
        }
    }

    private String DecodeToken()
    {
        return "";
    }
}

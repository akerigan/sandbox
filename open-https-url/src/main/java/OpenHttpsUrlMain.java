import java.io.*;
import java.net.URI;
import java.nio.channels.FileChannel;

/**
 * @author VVinichenko
 * @since 2012-03-30 16:55
 */
public class OpenHttpsUrlMain {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Usage: java -jar open-https-url.jar URL");
            System.exit(1);
        }
        URI uri = URI.create(args[0]);
        try {
            InputStream inputStream = uri.toURL().openStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            copyCompletely(inputStream, baos);
            System.out.println("Url '" + args[0] + "'opened successfully: " + baos.toByteArray().length + " bytes read");
        } catch (IOException e) {
            System.out.println("Cant open url '" + args[0] + "', error: " + e.getMessage());
        }
    }

    public static void copyCompletely(InputStream input, OutputStream output) throws IOException {
        // if both are file streams, use channel IO
        if ((output instanceof FileOutputStream) && (input instanceof FileInputStream)) {
            try {
                FileChannel target = ((FileOutputStream) output).getChannel();
                FileChannel source = ((FileInputStream) input).getChannel();

                source.transferTo(0, Integer.MAX_VALUE, target);

                source.close();
                target.close();

                return;
            } catch (Exception e) { /* failover to byte stream version */
            }
        }

        byte[] buf = new byte[8192];
        while (true) {
            int length = input.read(buf);
            if (length < 0)
                break;
            output.write(buf, 0, length);
        }

        try {
            input.close();
        } catch (IOException ignore) {
        }
        try {
            output.close();
        } catch (IOException ignore) {
        }
    }
}

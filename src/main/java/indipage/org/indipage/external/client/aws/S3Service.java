package indipage.org.indipage.external.client.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.BadRequestException;
import indipage.org.indipage.exception.model.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    private final ArrayList<String> allowedFileExtension = new ArrayList<>(
            List.of(".jpg", "jpeg", ".png", ".JPG", ".JPEG", ".PNG"));

    @PostConstruct
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

    public String uploadIamge(MultipartFile multipartFile, String folder) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(
                    new PutObjectRequest(bucket + folder + "/image", fileName, inputStream,
                            objectMetadata).withCannedAcl(
                            CannedAccessControlList.PublicRead));
            return amazonS3.getUrl(bucket + folder + "/image", fileName).toString();

        } catch (IOException e) {
            throw new NotFoundException(Error.NOT_FOUND_SAVED_IMAGE_EXCEPTION,
                    Error.NOT_FOUND_SAVED_IMAGE_EXCEPTION.getMessage());
        }
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        if (fileName.length() == 0) {
            throw new NotFoundException(Error.NOT_FOUND_IMAGE_EXCEPTION, Error.NOT_FOUND_IMAGE_EXCEPTION.getMessage());
        }
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!allowedFileExtension.contains(idxFileName)) {
            throw new BadRequestException(Error.INVALID_MULTIPART_EXTENSION_EXCEPTION,
                    Error.INVALID_MULTIPART_EXTENSION_EXCEPTION.getMessage());
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}

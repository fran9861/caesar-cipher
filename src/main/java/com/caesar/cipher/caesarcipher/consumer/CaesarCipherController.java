package com.caesar.cipher.caesarcipher.consumer;

import com.caesar.cipher.caesarcipher.crosscutting.ConstantPath;
import com.caesar.cipher.caesarcipher.openapi.CaesarCipherApi;
import com.caesar.cipher.caesarcipher.openapi.model.SendRequestCaesarCipher;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipher;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipherList;
import com.caesar.cipher.caesarcipher.service.ICaesarCipher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.esapi.logging.cleaning.NewlineLogScrubber;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(ConstantPath.BASE_PATH_V1)
@RestController
public class CaesarCipherController implements CaesarCipherApi {

    private static  final NewlineLogScrubber logClean = new NewlineLogScrubber();

    private final ICaesarCipher iCaesarCipher;

    @Override
    public ResponseEntity<SendResponseCaesarCipher> caesarCipher(SendRequestCaesarCipher sendRequestCaesarCipher) {
        String text = sendRequestCaesarCipher.getText();
        Integer rotationFactor = sendRequestCaesarCipher.getRotationFactor();
        log.info(logClean.cleanMessage(String.format("Entered /".concat(ConstantPath.CAESAR_CIPHER_PATH).concat("caesarCipher in CaesarCipherController"))));

        return iCaesarCipher.caesarCipherText(text,rotationFactor);

    }

    @Override
    public ResponseEntity<SendResponseCaesarCipherList> caesarCipherList(Integer page, Integer size) {
        log.info(logClean.cleanMessage(String.format("Entered /".concat(ConstantPath.CAESAR_CIPHER_LIST_PATH).concat("caesarCipherList in CaesarCipherController"))));

        return iCaesarCipher.caesarCipherList(page, size);
    }
}

package org.imethan.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;


/**
 * CodecFilter.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2016年3月16日下午3:57:12
 */
public class CodecFilter implements ProtocolCodecFactory {

    private final TextLineEncoder encoder;
    private final TextLineDecoder decoder;
    final static char endchar = 0x0d;

    public CodecFilter() {
        this(Charset.forName("UTF-8"));
    }

    public CodecFilter(Charset charset) {
        encoder = new TextLineEncoder(charset, LineDelimiter.UNIX);
        decoder = new TextLineDecoder(charset, LineDelimiter.AUTO);
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    public int getEncoderMaxLineLength() {
        return encoder.getMaxLineLength();
    }

    public void setEncoderMaxLineLength(int maxLineLength) {
        encoder.setMaxLineLength(maxLineLength);
    }

    public int getDecoderMaxLineLength() {
        return decoder.getMaxLineLength();
    }

    public void setDecoderMaxLineLength(int maxLineLength) {
        decoder.setMaxLineLength(maxLineLength);
    }

}

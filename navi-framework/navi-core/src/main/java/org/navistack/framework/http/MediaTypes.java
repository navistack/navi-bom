package org.navistack.framework.http;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MIME Types.
 *
 * @see <a href="http://svn.apache.org/repos/asf/httpd/httpd/trunk/docs/conf/mime.types">http://svn.apache.org/repos/asf/httpd/httpd/trunk/docs/conf/mime.types</a>
 */
@UtilityClass
public class MediaTypes {
    private final Map<String, MediaType> TYPES_POOL = new ConcurrentHashMap<>(186);

    public final MediaType APPLICATION_ANDREW_INSET = of("application", "andrew-inset", "ez");
    public final MediaType APPLICATION_APPLIXWARE = of("application", "applixware", "aw");
    public final MediaType APPLICATION_ATOM_XML = of("application", "atom+xml", "atom");
    public final MediaType APPLICATION_ATOMCAT_XML = of("application", "atomcat+xml", "atomcat");
    public final MediaType APPLICATION_ATOMSVC_XML = of("application", "atomsvc+xml", "atomsvc");
    public final MediaType APPLICATION_CCXML_XML = of("application", "ccxml+xml", "ccxml");
    public final MediaType APPLICATION_CDMI_CAPABILITY = of("application", "cdmi-capability", "cdmia");
    public final MediaType APPLICATION_CDMI_CONTAINER = of("application", "cdmi-container", "cdmic");
    public final MediaType APPLICATION_CDMI_DOMAIN = of("application", "cdmi-domain", "cdmid");
    public final MediaType APPLICATION_CDMI_OBJECT = of("application", "cdmi-object", "cdmio");
    public final MediaType APPLICATION_CDMI_QUEUE = of("application", "cdmi-queue", "cdmiq");
    public final MediaType APPLICATION_CU_SEEME = of("application", "cu-seeme", "cu");
    public final MediaType APPLICATION_DAVMOUNT_XML = of("application", "davmount+xml", "davmount");
    public final MediaType APPLICATION_DOCBOOK_XML = of("application", "docbook+xml", "dbk");
    public final MediaType APPLICATION_DSSC_DER = of("application", "dssc+der", "dssc");
    public final MediaType APPLICATION_DSSC_XML = of("application", "dssc+xml", "xdssc");
    public final MediaType APPLICATION_ECMASCRIPT = of("application", "ecmascript", "ecma");
    public final MediaType APPLICATION_EMMA_XML = of("application", "emma+xml", "emma");
    public final MediaType APPLICATION_EPUB_ZIP = of("application", "epub+zip", "epub");
    public final MediaType APPLICATION_EXI = of("application", "exi", "exi");
    public final MediaType APPLICATION_FONT_TDPFR = of("application", "font-tdpfr", "pfr");
    public final MediaType APPLICATION_GML_XML = of("application", "gml+xml", "gml");
    public final MediaType APPLICATION_GPX_XML = of("application", "gpx+xml", "gpx");
    public final MediaType APPLICATION_GXF = of("application", "gxf", "gxf");
    public final MediaType APPLICATION_HYPERSTUDIO = of("application", "hyperstudio", "stk");
    public final MediaType APPLICATION_INKML_XML = of("application", "inkml+xml", "ink", "inkml");
    public final MediaType APPLICATION_IPFIX = of("application", "ipfix", "ipfix");
    public final MediaType APPLICATION_JAVA_ARCHIVE = of("application", "java-archive", "jar");
    public final MediaType APPLICATION_JAVA_SERIALIZED_OBJECT = of("application", "java-serialized-object", "ser");
    public final MediaType APPLICATION_JAVA_VM = of("application", "java-vm", "class");
    public final MediaType APPLICATION_JSON = of("application", "json", "json");
    public final MediaType APPLICATION_JSONML_JSON = of("application", "jsonml+json", "jsonml");
    public final MediaType APPLICATION_LOST_XML = of("application", "lost+xml", "lostxml");
    public final MediaType APPLICATION_MAC_BINHEX40 = of("application", "mac-binhex40", "hqx");
    public final MediaType APPLICATION_MAC_COMPACTPRO = of("application", "mac-compactpro", "cpt");
    public final MediaType APPLICATION_MADS_XML = of("application", "mads+xml", "mads");
    public final MediaType APPLICATION_MARC = of("application", "marc", "mrc");
    public final MediaType APPLICATION_MARCXML_XML = of("application", "marcxml+xml", "mrcx");
    public final MediaType APPLICATION_MATHEMATICA = of("application", "mathematica", "ma", "nb", "mb");
    public final MediaType APPLICATION_MATHML_XML = of("application", "mathml+xml", "mathml");
    public final MediaType APPLICATION_MBOX = of("application", "mbox", "mbox");
    public final MediaType APPLICATION_MEDIASERVERCONTROL_XML = of("application", "mediaservercontrol+xml", "mscml");
    public final MediaType APPLICATION_METALINK_XML = of("application", "metalink+xml", "metalink");
    public final MediaType APPLICATION_METALINK4_XML = of("application", "metalink4+xml", "meta4");
    public final MediaType APPLICATION_METS_XML = of("application", "mets+xml", "mets");
    public final MediaType APPLICATION_MODS_XML = of("application", "mods+xml", "mods");
    public final MediaType APPLICATION_MP21 = of("application", "mp21", "m21", "mp21");
    public final MediaType APPLICATION_MP4 = of("application", "mp4", "mp4s");
    public final MediaType APPLICATION_MSWORD = of("application", "msword", "doc", "dot");
    public final MediaType APPLICATION_MXF = of("application", "mxf", "mxf");
    public final MediaType APPLICATION_OCTET_STREAM = of("application", "octet-stream",
            "bin", "dms", "lrf", "mar", "so", "dist", "distz", "pkg", "bpk", "dump", "elc", "deploy");
    public final MediaType APPLICATION_ODA = of("application", "oda", "oda");
    public final MediaType APPLICATION_OEBPS_PACKAGE_XML = of("application", "oebps-package+xml", "opf");
    public final MediaType APPLICATION_OGG = of("application", "ogg", "ogx");
    public final MediaType APPLICATION_OMDOC_XML = of("application", "omdoc+xml", "omdoc");
    public final MediaType APPLICATION_ONENOTE = of("application", "onenote", "onetoc", "onetoc2", "onetmp", "onepkg");
    public final MediaType APPLICATION_OXPS = of("application", "oxps", "oxps");
    public final MediaType APPLICATION_PATCH_OPS_ERROR_XML = of("application", "patch-ops-error+xml", "xer");
    public final MediaType APPLICATION_PDF = of("application", "pdf", "pdf");
    public final MediaType APPLICATION_PGP_ENCRYPTED = of("application", "pgp-encrypted", "pgp");
    public final MediaType APPLICATION_PGP_SIGNATURE = of("application", "pgp-signature", "asc", "sig");
    public final MediaType APPLICATION_PICS_RULES = of("application", "pics-rules", "prf");
    public final MediaType APPLICATION_PKCS10 = of("application", "pkcs10", "p10");
    public final MediaType APPLICATION_PKCS7_MIME = of("application", "pkcs7-mime", "p7m", "p7c");
    public final MediaType APPLICATION_PKCS7_SIGNATURE = of("application", "pkcs7-signature", "p7s");
    public final MediaType APPLICATION_PKCS8 = of("application", "pkcs8", "p8");
    public final MediaType APPLICATION_PKIXCMP = of("application", "pkixcmp", "pki");
    public final MediaType APPLICATION_PLS_XML = of("application", "pls+xml", "pls");
    public final MediaType APPLICATION_POSTSCRIPT = of("application", "postscript", "ai", "eps", "ps");
    public final MediaType APPLICATION_PRS_CWW = of("application", "prs.cww", "cww");
    public final MediaType APPLICATION_PSKC_XML = of("application", "pskc+xml", "pskcxml");
    public final MediaType APPLICATION_RDF_XML = of("application", "rdf+xml", "rdf");
    public final MediaType APPLICATION_REGINFO_XML = of("application", "reginfo+xml", "rif");
    public final MediaType APPLICATION_RESOURCE_LISTS_XML = of("application", "resource-lists+xml", "rl");
    public final MediaType APPLICATION_RESOURCE_LISTS_DIFF_XML = of("application", "resource-lists-diff+xml", "rld");
    public final MediaType APPLICATION_RLS_SERVICES_XML = of("application", "rls-services+xml", "rs");
    public final MediaType APPLICATION_RPKI_GHOSTBUSTERS = of("application", "rpki-ghostbusters", "gbr");
    public final MediaType APPLICATION_RPKI_MANIFEST = of("application", "rpki-manifest", "mft");
    public final MediaType APPLICATION_RPKI_ROA = of("application", "rpki-roa", "roa");
    public final MediaType APPLICATION_RSD_XML = of("application", "rsd+xml", "rsd");
    public final MediaType APPLICATION_RSS_XML = of("application", "rss+xml", "rss");
    public final MediaType APPLICATION_RTF = of("application", "rtf", "rtf");
    public final MediaType APPLICATION_SBML_XML = of("application", "sbml+xml", "sbml");
    public final MediaType APPLICATION_SCVP_CV_REQUEST = of("application", "scvp-cv-request", "scq");
    public final MediaType APPLICATION_SCVP_CV_RESPONSE = of("application", "scvp-cv-response", "scs");
    public final MediaType APPLICATION_SCVP_VP_REQUEST = of("application", "scvp-vp-request", "spq");
    public final MediaType APPLICATION_SCVP_VP_RESPONSE = of("application", "scvp-vp-response", "spp");
    public final MediaType APPLICATION_SDP = of("application", "sdp", "sdp");
    public final MediaType APPLICATION_SET_PAYMENT_INITIATION = of("application", "set-payment-initiation",
            "setpay");
    public final MediaType APPLICATION_SET_REGISTRATION_INITIATION = of("application", "set-registration-initiation",
            "setreg");
    public final MediaType APPLICATION_SHF_XML = of("application", "shf+xml", "shf");
    public final MediaType APPLICATION_SMIL_XML = of("application", "smil+xml", "smi", "smil");
    public final MediaType APPLICATION_SPARQL_QUERY = of("application", "sparql-query", "rq");
    public final MediaType APPLICATION_SPARQL_RESULTS_XML = of("application", "sparql-results+xml", "srx");
    public final MediaType APPLICATION_SRGS = of("application", "srgs", "gram");
    public final MediaType APPLICATION_SRGS_XML = of("application", "srgs+xml", "grxml");
    public final MediaType APPLICATION_SRU_XML = of("application", "sru+xml", "sru");
    public final MediaType APPLICATION_SSDL_XML = of("application", "ssdl+xml", "ssdl");
    public final MediaType APPLICATION_SSML_XML = of("application", "ssml+xml", "ssml");
    public final MediaType APPLICATION_TEI_XML = of("application", "tei+xml", "tei", "teicorpus");
    public final MediaType APPLICATION_THRAUD_XML = of("application", "thraud+xml", "tfi");
    public final MediaType APPLICATION_TIMESTAMPED_DATA = of("application", "timestamped-data", "tsd");
    public final MediaType APPLICATION_VOICEXML_XML = of("application", "voicexml+xml", "vxml");
    public final MediaType APPLICATION_WASM = of("application", "wasm", "wasm");
    public final MediaType APPLICATION_WIDGET = of("application", "widget", "wgt");
    public final MediaType APPLICATION_WINHLP = of("application", "winhlp", "hlp");
    public final MediaType APPLICATION_WSDL_XML = of("application", "wsdl+xml", "wsdl");
    public final MediaType APPLICATION_WSPOLICY_XML = of("application", "wspolicy+xml", "wspolicy");
    public final MediaType APPLICATION_XAML_XML = of("application", "xaml+xml", "xaml");
    public final MediaType APPLICATION_XCAP_DIFF_XML = of("application", "xcap-diff+xml", "xdf");
    public final MediaType APPLICATION_XENC_XML = of("application", "xenc+xml", "xenc");
    public final MediaType APPLICATION_XHTML_XML = of("application", "xhtml+xml", "xhtml", "xht");
    public final MediaType APPLICATION_XML = of("application", "xml", "xml", "xsl");
    public final MediaType APPLICATION_XML_DTD = of("application", "xml-dtd", "dtd");
    public final MediaType APPLICATION_XOP_XML = of("application", "xop+xml", "xop");
    public final MediaType APPLICATION_XPROC_XML = of("application", "xproc+xml", "xpl");
    public final MediaType APPLICATION_XSLT_XML = of("application", "xslt+xml", "xslt");
    public final MediaType APPLICATION_XSPF_XML = of("application", "xspf+xml", "xspf");
    public final MediaType APPLICATION_XV_XML = of("application", "xv+xml", "mxml", "xhvml", "xvml", "xvm");
    public final MediaType APPLICATION_YANG = of("application", "yang", "yang");
    public final MediaType APPLICATION_YIN_XML = of("application", "yin+xml", "yin");
    public final MediaType APPLICATION_ZIP = of("application", "zip", "zip");
    public final MediaType AUDIO_ADPCM = of("audio", "adpcm", "adp");
    public final MediaType AUDIO_BASIC = of("audio", "basic", "au", "snd");
    public final MediaType AUDIO_MIDI = of("audio", "midi", "mid", "midi", "kar", "rmi");
    public final MediaType AUDIO_MP4 = of("audio", "mp4", "m4a", "mp4a");
    public final MediaType AUDIO_MPEG = of("audio", "mpeg", "mpga", "mp2", "mp2a", "mp3", "m2a", "m3a");
    public final MediaType AUDIO_OGG = of("audio", "ogg", "oga", "ogg", "spx", "opus");
    public final MediaType AUDIO_S3M = of("audio", "s3m", "s3m");
    public final MediaType AUDIO_SILK = of("audio", "silk", "sil");
    public final MediaType AUDIO_WEBM = of("audio", "webm", "weba");
    public final MediaType AUDIO_XM = of("audio", "xm", "xm");
    public final MediaType FONT_COLLECTION = of("font", "collection", "ttc");
    public final MediaType FONT_OTF = of("font", "otf", "otf");
    public final MediaType FONT_TTF = of("font", "ttf", "ttf");
    public final MediaType FONT_WOFF = of("font", "woff", "woff");
    public final MediaType FONT_WOFF2 = of("font", "woff2", "woff2");
    public final MediaType IMAGE_BMP = of("image", "bmp", "bmp");
    public final MediaType IMAGE_CGM = of("image", "cgm", "cgm");
    public final MediaType IMAGE_G3FAX = of("image", "g3fax", "g3");
    public final MediaType IMAGE_GIF = of("image", "gif", "gif");
    public final MediaType IMAGE_IEF = of("image", "ief", "ief");
    public final MediaType IMAGE_JPEG = of("image", "jpeg", "jpeg", "jpg", "jpe");
    public final MediaType IMAGE_KTX = of("image", "ktx", "ktx");
    public final MediaType IMAGE_PNG = of("image", "png", "png");
    public final MediaType IMAGE_PRS_BTIF = of("image", "prs.btif", "btif");
    public final MediaType IMAGE_SGI = of("image", "sgi", "sgi");
    public final MediaType IMAGE_SVG_XML = of("image", "svg+xml", "svg", "svgz");
    public final MediaType IMAGE_TIFF = of("image", "tiff", "tiff", "tif");
    public final MediaType IMAGE_WEBP = of("image", "webp", "webp");
    public final MediaType MESSAGE_RFC822 = of("message", "rfc822", "eml", "mime");
    public final MediaType MODEL_IGES = of("model", "iges", "igs", "iges");
    public final MediaType MODEL_MESH = of("model", "mesh", "msh", "mesh", "silo");
    public final MediaType MODEL_VRML = of("model", "vrml", "wrl", "vrml");
    public final MediaType MODEL_X3D_BINARY = of("model", "x3d+binary", "x3db", "x3dbz");
    public final MediaType MODEL_X3D_VRML = of("model", "x3d+vrml", "x3dv", "x3dvz");
    public final MediaType MODEL_X3D_XML = of("model", "x3d+xml", "x3d", "x3dz");
    public final MediaType TEXT_CACHE_MANIFEST = of("text", "cache-manifest", "appcache");
    public final MediaType TEXT_CALENDAR = of("text", "calendar", "ics", "ifb");
    public final MediaType TEXT_CSS = of("text", "css", "css");
    public final MediaType TEXT_CSV = of("text", "csv", "csv");
    public final MediaType TEXT_HTML = of("text", "html", "html", "htm");
    public final MediaType TEXT_JAVASCRIPT = of("text", "javascript", "js", "mjs");
    public final MediaType TEXT_N3 = of("text", "n3", "n3");
    public final MediaType TEXT_PLAIN = of("text", "plain", "txt", "text", "conf", "def", "list", "log", "in");
    public final MediaType TEXT_PRS_LINES_TAG = of("text", "prs.lines.tag", "dsc");
    public final MediaType TEXT_RICHTEXT = of("text", "richtext", "rtx");
    public final MediaType TEXT_SGML = of("text", "sgml", "sgml", "sgm");
    public final MediaType TEXT_TAB_SEPARATED_VALUES = of("text", "tab-separated-values", "tsv");
    public final MediaType TEXT_TROFF = of("text", "troff", "t", "tr", "roff", "man", "me", "ms");
    public final MediaType TEXT_TURTLE = of("text", "turtle", "ttl");
    public final MediaType TEXT_URI_LIST = of("text", "uri-list", "uri", "uris", "urls");
    public final MediaType TEXT_VCARD = of("text", "vcard", "vcard");
    public final MediaType VIDEO_3GPP = of("video", "3gpp", "3gp");
    public final MediaType VIDEO_3GPP2 = of("video", "3gpp2", "3g2");
    public final MediaType VIDEO_H261 = of("video", "h261", "h261");
    public final MediaType VIDEO_H263 = of("video", "h263", "h263");
    public final MediaType VIDEO_H264 = of("video", "h264", "h264");
    public final MediaType VIDEO_JPEG = of("video", "jpeg", "jpgv");
    public final MediaType VIDEO_JPM = of("video", "jpm", "jpm", "jpgm");
    public final MediaType VIDEO_MJ2 = of("video", "mj2", "mj2", "mjp2");
    public final MediaType VIDEO_MP4 = of("video", "mp4", "mp4", "mp4v", "mpg4");
    public final MediaType VIDEO_MPEG = of("video", "mpeg", "mpeg", "mpg", "mpe", "m1v", "m2v");
    public final MediaType VIDEO_OGG = of("video", "ogg", "ogv");
    public final MediaType VIDEO_QUICKTIME = of("video", "quicktime", "qt", "mov");
    public final MediaType VIDEO_WEBM = of("video", "webm", "webm");

    public MediaType of(String type, String subType, Set<String> extensions) {
        MediaType mediaType = new MediaType(type, subType, extensions);
        TYPES_POOL.put(mediaType.getFullType(), mediaType);
        return mediaType;
    }

    public MediaType of(String type, String subType, Collection<? extends String> extensions) {
        return of(type, subType, new HashSet<>(extensions));
    }

    public MediaType of(String type, String subType, String... extensions) {
        if (extensions == null || extensions.length == 0) {
            return of(type, subType, java.util.Collections.emptySet());
        } else {
            return of(type, subType, new HashSet<>(Arrays.asList(extensions)));
        }
    }

    public MediaType of(String type, String subType) {
        return of(type, subType, Collections.emptySet());
    }

    public MediaType valueOf(String fullType) {
        return TYPES_POOL.get(fullType);
    }

    public MediaType valueOf(String type, String subType) {
        return valueOf(type + "/" + subType);
    }

    public Optional<MediaType> optionalValueOf(String fullType) {
        return Optional.ofNullable(valueOf(fullType));
    }

    public Optional<MediaType> optionalValueOf(String type, String subType) {
        return Optional.ofNullable(valueOf(type, subType));
    }
}

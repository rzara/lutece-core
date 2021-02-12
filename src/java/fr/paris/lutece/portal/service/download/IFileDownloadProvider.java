package fr.paris.lutece.portal.service.download;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.api.user.User;
import fr.paris.lutece.portal.business.file.File;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
 *  Provider for download of {@link File}
 */
public interface IFileDownloadProvider
{
    String getProviderName( );
    
    /**
     * Generates an encrypted download url for a {@link File}.
     * @param fileDownloadData
     * @param urlBo
     * @return
     */
    String getDownloadUrl( FileDownloadData fileDownloadData, boolean urlBo );
    
    /**
     * Decrypts download parameters and get the file to download
     * @param user
     * @param request
     * @return
     * @throws SiteMessageException
     */
    File getFile( User user, HttpServletRequest request, boolean bo ) throws SiteMessageException, UserNotSignedException;
    
    /**
     * Number of minutes the links generated by this provider are valid.
     * @return
     */
    default int getLinkValidityTime( )
    {
        return Integer.parseInt( AppPropertiesService.getProperty( "lutece.file.download.validity", "0" ) );
    }
}

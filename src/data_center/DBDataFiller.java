package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Can both fill data into database and into the bbks */
public class DBDataFiller
{
	public static void dbIntoMain(ResultSet resultSet, BbkOutline bbkOutline) throws SQLException
    {	
		bbkOutline.name = resultSet.getString(DBConsts.Header.Main.NAME);
		bbkOutline.type = resultSet.getString(DBConsts.Header.Main.TYPE);
		bbkOutline.author = resultSet.getString(DBConsts.Header.Main.AUTHOR);
		bbkOutline.enterDate = resultSet.getString(DBConsts.Header.Main.ENTER_DATE);
		bbkOutline.shortDesc = resultSet.getString(DBConsts.Header.Main.SHORT_DESC);
		bbkOutline.url = resultSet.getString(DBConsts.Header.Main.URL);
	    
		bbkOutline.rating = new BbkOutline.Rating(resultSet);
	    
		bbkOutline.ID = resultSet.getString(DBConsts.Header.Main.ID);
		bbkOutline.shortName = resultSet.getString(DBConsts.Header.Main.SHORT_NAME);
		bbkOutline.releaseStatus = resultSet.getString(DBConsts.Header.Main.RELEASE_STATUS);
		bbkOutline.sampleStatus = resultSet.getString(DBConsts.Header.Main.SAMPLE_STATUS);
		bbkOutline.results = resultSet.getString(DBConsts.Header.Main.RESULTS);
		bbkOutline.nickname = resultSet.getString(DBConsts.Header.Main.NICKNAME);
		bbkOutline.part_rating = resultSet.getString(DBConsts.Header.Main.PART_RATING);
		bbkOutline.sequence = resultSet.getString(DBConsts.Header.Main.SEQUENCE);
		bbkOutline.samples = resultSet.getString(DBConsts.Header.Main.SAMPLES);
		bbkOutline.references = resultSet.getString(DBConsts.Header.Main.REFERENCES);
		bbkOutline.groups = resultSet.getString(DBConsts.Header.Main.GROUPS);
		bbkOutline.DNA_status = resultSet.getString(DBConsts.Header.Main.DNA_STATUS);
		bbkOutline.groupFavorite = resultSet.getString(DBConsts.Header.Main.GROUP_FAVOURITE);
    }
	
	/** Fill data from database, don't need url, rating parts etc..., cause
	 * they are initialized when newed, and are constants */
	public static void dbIntoMain(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
    {	
		bbkUpload.fillPrivateData(resultSet);
		bbkUpload.type = resultSet.getString(DBConsts.Header.Main.TYPE);
		bbkUpload.author = resultSet.getString(DBConsts.Header.Main.AUTHOR);
		bbkUpload.shortDesc = resultSet.getString(DBConsts.Header.Main.SHORT_DESC);
		bbkUpload.nickname = resultSet.getString(DBConsts.Header.Main.NICKNAME);
		bbkUpload.groupFavorite = resultSet.getString(DBConsts.Header.Main.GROUP_FAVOURITE);
		bbkUpload.delete_this_part = resultSet.getString(DBConsts.Header.Main.DELETE_THIS_PART);
    }
	
	
	
	// resultSet WILL have an result if enters these functions, checked outside
	public static void dbIntoCategories(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.categories.add(new BbkDetail.Category(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoCategories(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.categories.add(new BbkUpload.Category(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoDeepSubparts(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.deepSubparts.add(new BbkDetail.DeepSubpart(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoDeepSubparts(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.deepSubparts.add(new BbkUpload.DeepSubpart(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoFeatures(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.features.add(new BbkDetail.Feature(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoFeatures(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.features.add(new BbkUpload.Feature(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoParameters(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.parameters.add(new BbkDetail.Parameter(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoParameters(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.parameters.add(new BbkUpload.Parameter(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoSpecifiedSubparts(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.specifiedSubparts.add(new BbkDetail.SpecifiedSubpart(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoSpecifiedSubparts(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.specifiedSubparts.add(new BbkUpload.SpecifiedSubpart(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoSpecifiedSubscars(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.specifiedSubscars.add(new BbkDetail.SpecifiedSubscar(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoSpecifiedSubscars(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.specifiedSubscars.add(new BbkUpload.SpecifiedSubscar(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoTwins(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.twins.add(new BbkDetail.Twin(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoTwins(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.twins.add(new BbkUpload.Twin(resultSet));
		while (resultSet.next());
	}
	
	
	
	
	
	public static void mainIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + DBConsts.Table.MAIN_UPLOAD + " where " + 
				DBConsts.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		statement.execute("insert into " + DBConsts.Table.MAIN_UPLOAD + " " +
			"(" + DBConsts.Header.Main.NAME + ", " +
				  DBConsts.Header.Main.ID + ", " +
				  DBConsts.Header.Main.ENTER_DATE + ", " + 
				  DBConsts.Header.Main.SHORT_NAME + ", " +
				  DBConsts.Header.Main.SEQUENCE + ", " + 
				  DBConsts.Header.Main.TYPE + ", " + 
				  DBConsts.Header.Main.AUTHOR + ", " + 
				  DBConsts.Header.Main.SHORT_DESC + ", " + 
				  DBConsts.Header.Main.NICKNAME + ", " + 
				  DBConsts.Header.Main.GROUP_FAVOURITE + ", " +
				  DBConsts.Header.Main.DELETE_THIS_PART + ")" + 
			" values " + 
			"(" + "'" + bbkUpload.getName() + "'" + ", " + 
				  "'" + bbkUpload.getID() + "'" + ", " +
				  "'" + bbkUpload.getEnterDate() + "'" + ", " + 
				  "'" + bbkUpload.getShortName() + "'" + ", " +
				  "'" + bbkUpload.getSequence() + "'" + ", " + 
				  "'" + bbkUpload.type + "'" + ", " + 
				  "'" + bbkUpload.author + "'" + ", " + 
				  "'" + bbkUpload.shortDesc + "'" + ", " + 
				  "'" + bbkUpload.nickname + "'" + ", " + 
				  "'" + bbkUpload.groupFavorite + "'" + ", " + 
				  "'" + bbkUpload.delete_this_part + "'" + ")");
	}
	
	public static void categoriesIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + DBConsts.Table.CATEGORIES_UPLOAD + " where " + 
				DBConsts.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.Category category : bbkUpload.categories)	
			statement.execute("insert into " + DBConsts.Table.CATEGORIES_UPLOAD + " " +
				"(" + DBConsts.Header.Category.CATEGORY + ")" + 
				" values " + 
				"(" + "'" + category.category + "'" + ")");
	}
	
	public static void deepSubpartsIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + DBConsts.Table.DEEP_SUBPARTS_UPLOAD + " where " + 
				DBConsts.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.DeepSubpart deepSubpart : bbkUpload.deepSubparts)	
			statement.execute("insert into " + DBConsts.Table.DEEP_SUBPARTS_UPLOAD + " " +
				"(" + DBConsts.Header.DeepSub.ID + ", " + 
					  DBConsts.Header.DeepSub.NAME + ", " + 
					  DBConsts.Header.DeepSub.NICKNAME + ", " + 
					  DBConsts.Header.DeepSub.SHORT_DESC + ", " + 
					  DBConsts.Header.DeepSub.TYPE + ")" + 
				" values " + 
				"(" + "'" + deepSubpart.ID + "'" + ", " +
					  "'" + deepSubpart.name + "'" + ", " +
					  "'" + deepSubpart.nickname + "'" + ", " +
					  "'" + deepSubpart.shortDesc + "'" + ", " +
					  "'" + deepSubpart.type + "'" + ")");
	}
	
	public static void featuresIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + DBConsts.Table.FEATURES_UPLOAD + " where " + 
				DBConsts.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.Feature feature : bbkUpload.features)	
			statement.execute("insert into " + DBConsts.Table.FEATURES_UPLOAD + " " +
				"(" + DBConsts.Header.Feature.DIRECTION + ", " + 
					  DBConsts.Header.Feature.ID + ", " + 
					  DBConsts.Header.Feature.START_POS + ", " + 
					  DBConsts.Header.Feature.END_POS + ", " + 
					  DBConsts.Header.Feature.TITLE + ", " + 
					  DBConsts.Header.Feature.TYPE + ")" + 
				" values " + 
				"(" + "'" + feature.direction + "'" + ", " + 
					  "'" + feature.ID + "'" + ", " + 
					  "'" + feature.startPos + "'" + ", " + 
					  "'" + feature.endPos + "'" + ", " + 
					  "'" + feature.title + "'" + ", " + 
					  "'" + feature.type + "'" + ")");
	}
	
	public static void parametersIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + DBConsts.Table.PARAMETERS_UPLOAD + " where " + 
				DBConsts.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.Parameter parameter : bbkUpload.parameters)	
			statement.execute("insert into " + DBConsts.Table.PARAMETERS_UPLOAD + " " +
				"(" + DBConsts.Header.Parameter.ID + ", " + 
					  DBConsts.Header.Parameter.M_DATE + ", " + 
					  DBConsts.Header.Parameter.NAME + ", " + 
					  DBConsts.Header.Parameter.UNITS + ", " + 
					  DBConsts.Header.Parameter.URL + ", " + 
					  DBConsts.Header.Parameter.USER_ID + ", " + 
					  DBConsts.Header.Parameter.USER_NAME + ")" + 
				" values " + 
				"(" + "'" + parameter.ID + "'" + ", " + 
					  "'" + parameter.m_date + "'" + ", " + 
					  "'" + parameter.name + "'" + ", " + 
					  "'" + parameter.units + "'" + ", " + 
					  "'" + parameter.url + "'" + ", " + 
					  "'" + parameter.userID + "'" + ", " + 
					  "'" + parameter.userName + "'" + ")");
	}
	
	public static void specifiedSubpartsIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + DBConsts.Table.SPECIFIED_SUBPARTS_UPLOAD + " where " + 
				DBConsts.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.SpecifiedSubpart subpart : bbkUpload.specifiedSubparts)	
			statement.execute("insert into " + DBConsts.Table.SPECIFIED_SUBPARTS_UPLOAD + " " +
				"(" + DBConsts.Header.SpecSub.ID + ", " + 
					  DBConsts.Header.SpecSub.NAME + ", " + 
					  DBConsts.Header.SpecSub.NICKNAME + ", " + 
					  DBConsts.Header.SpecSub.SHORT_DESC + ", " + 
					  DBConsts.Header.SpecSub.TYPE + ")" + 
				" values " + 
				"(" + "'" + subpart.ID + "'" + ", " + 
					  "'" + subpart.name + "'" + ", " + 
					  "'" + subpart.nickname + "'" + ", " + 
					  "'" + subpart.shortDesc + "'" + ", " + 
					  "'" + subpart.type + "'" + ")");
	}
	
	public static void specifiedSubscarsIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + DBConsts.Table.SPECIFIED_SUBSCARS_UPLOAD + " where " + 
				DBConsts.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.SpecifiedSubscar subscar : bbkUpload.specifiedSubscars)	
			statement.execute("insert into " + DBConsts.Table.SPECIFIED_SUBSCARS_UPLOAD + " " +
				"(" + DBConsts.Header.SpecScar.COMMENTS + ", " + 
					  DBConsts.Header.SpecScar.ID + ", " + 
					  DBConsts.Header.SpecScar.NAME + ", " + 
					  DBConsts.Header.SpecScar.NICK_NAME + ", " + 
					  DBConsts.Header.SpecScar.SEQUENCE + ", " + 
					  DBConsts.Header.SpecScar.STANDARD + ", " + 
					  DBConsts.Header.SpecScar.TYPE + ")" + 
				" values " + 
				"(" + "'" + subscar.comments + "'" + ", " + 
					  "'" + subscar.ID + "'" + ", " + 
					  "'" + subscar.name + "'" + ", " + 
					  "'" + subscar.nickname + "'" + ", " + 
					  "'" + subscar.sequence + "'" + ", " + 
					  "'" + subscar.standard + "'" + ", " + 
					  "'" + subscar.type + "'" + ")");
	}
	
	public static void twinsIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + DBConsts.Table.TWINS_UPLOAD + " where " + 
				DBConsts.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.Twin twin : bbkUpload.twins)	
			statement.execute("insert into " + DBConsts.Table.TWINS_UPLOAD + " " +
				"(" + DBConsts.Header.Twin.TWIN + ")" + 
				" values " + 
				"(" + "'" + twin.twin + "'" + ")");
	}
	
}

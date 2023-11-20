package boardProject.entities.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 845690060L;

    public static final QBoard board = new QBoard("board");

    public final boardProject.entities.QBaseMemberEntity _super = new boardProject.entities.QBaseMemberEntity(this);

    public final StringPath bId = createString("bId");

    public final StringPath bName = createString("bName");

    public final StringPath category = createString("category");

    public final EnumPath<boardProject.commons.constants.Role> commentAccessRole = createEnum("commentAccessRole", boardProject.commons.constants.Role.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final EnumPath<boardProject.commons.constants.Role> listAccessRole = createEnum("listAccessRole", boardProject.commons.constants.Role.class);

    public final StringPath locationAfterWriting = createString("locationAfterWriting");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final EnumPath<boardProject.commons.constants.Role> replyAccessRole = createEnum("replyAccessRole", boardProject.commons.constants.Role.class);

    public final NumberPath<Integer> rowsOfPage = createNumber("rowsOfPage", Integer.class);

    public final BooleanPath showViewList = createBoolean("showViewList");

    public final StringPath skin = createString("skin");

    public final BooleanPath use = createBoolean("use");

    public final BooleanPath useAttachFile = createBoolean("useAttachFile");

    public final BooleanPath useAttachImage = createBoolean("useAttachImage");

    public final BooleanPath useComment = createBoolean("useComment");

    public final BooleanPath useEditor = createBoolean("useEditor");

    public final BooleanPath useReply = createBoolean("useReply");

    public final EnumPath<boardProject.commons.constants.Role> ViewAccessRole = createEnum("ViewAccessRole", boardProject.commons.constants.Role.class);

    public final EnumPath<boardProject.commons.constants.Role> writeAccessRole = createEnum("writeAccessRole", boardProject.commons.constants.Role.class);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}


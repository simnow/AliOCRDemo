/**
 * Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or https://ckeditor.com/legal/ckeditor-oss-license
 */

/* exported initSample */

if ( CKEDITOR.env.ie && CKEDITOR.env.version < 9 )
	CKEDITOR.tools.enableHtml5Elements( document );

// The trick to keep the editor in the sample quite small
// unless user specified own height.
CKEDITOR.config.height = 150;
CKEDITOR.config.width = 'auto';

var initSample = ( function() {
	var wysiwygareaAvailable = isWysiwygareaAvailable(),
		isBBCodeBuiltIn = !!CKEDITOR.plugins.get( 'bbcode' );

	return function() {
		var editorElement = CKEDITOR.document.getById( 'editor' );

		// :(((
		if ( isBBCodeBuiltIn ) {
			editorElement.setHtml(
				'Hello world!\n\n' +
				'I\'m an instance of [url=https://ckeditor.com]CKEditor[/url].'
			);
		}

		// Depending on the wysiwygare plugin availability initialize classic or inline editor.
		if ( wysiwygareaAvailable ) {
			CKEDITOR.replace( 'editor' );
		} else {
			editorElement.setAttribute( 'contenteditable', 'true' );
			CKEDITOR.inline( 'editor' );

			// TODO we can consider displaying some info box that
			// without wysiwygarea the classic editor may not work.
		}
	};

	function isWysiwygareaAvailable() {
		// If in development mode, then the wysiwygarea must be available.
		// Split REV into two strings so builder does not replace it :D.
		if ( CKEDITOR.revision == ( '%RE' + 'V%' ) ) {
			return true;
		}

		return !!CKEDITOR.plugins.get( 'wysiwygarea' );
	}
	
} )();

//测试添加粘贴
function uplaodImage(){
    CKEDITOR.instances.editor.on('change',function(e){
              var a = e.editor.document ;
              var b = a.find("img");
              var count = b.count();
              for(var i=0;i<count;i++){
                       var src =b.getItem(i).$.src;//获取img的src
                       if(src.substring(0,4)=='data'){
                                var url = 后台请求路径;
                                $.ajax({
                                type:"POST",
                                url:url,
                                async:false,//同步，因为修改编辑器内容的时候会多次调用change方法，所以要同步，否则会多次调用后台
                                data:{'src':src},
                                success:function(json){
                                                   var data = eval("("+json+")");
                                                   if(data.success){
                                                            b.getItem(i).$.src=data.url;

                                                   }
                                          }
                                });
                       }
              }

    });

}

